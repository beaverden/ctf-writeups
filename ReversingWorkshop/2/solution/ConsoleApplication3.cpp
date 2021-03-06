// ConsoleApplication3.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <Windows.h>
#include <atlbase.h>
#include <shlobj.h>

struct DudeCryptContext {
	HCRYPTKEY hKey;
	HCRYPTPROV hProv;
	HCRYPTHASH phHash;

	DWORD blockLength = 0;
	DWORD blockLengthLen = 4;
};

LPWSTR cat_strings(LPWSTR str1, LPWSTR str2) {
	LPWSTR result = new WCHAR[lstrlenW(str1) + lstrlenW(str2) + 1]();
	result[0] = '\0';
	lstrcatW(result, str1);
	lstrcatW(result, str2);
	return result;
}

LPWSTR cat_strings(LPWSTR str1, LPWSTR str2, LPWSTR str3) {
	LPWSTR temp = cat_strings(str2, str3);
	LPWSTR result = cat_strings(str1, temp);
	delete temp;
	return result;
}

void process_file(HANDLE hFoundFile, LPWIN32_FIND_DATA file_data, LPWSTR path, DudeCryptContext* ctx)
{
	if (hFoundFile == INVALID_HANDLE_VALUE) return;

	LPWSTR file_path = cat_strings(path, file_data->cFileName);
	LPWSTR decrypted_path = cat_strings((LPWSTR)path, file_data->cFileName, (LPWSTR)L"_decrypted");
	HANDLE hFile;

	HANDLE hRead = CreateFileW(
		file_path,
		GENERIC_READ,
		FILE_SHARE_READ,
		0,
		OPEN_EXISTING,
		FILE_ATTRIBUTE_NORMAL,
		NULL
	);
	HANDLE hWrite = CreateFileW(
		decrypted_path,
		GENERIC_WRITE,
		FILE_SHARE_READ,
		0,
		CREATE_ALWAYS,
		FILE_ATTRIBUTE_NORMAL,
		NULL
	);

	if (hRead == INVALID_HANDLE_VALUE || hRead == INVALID_HANDLE_VALUE) return;

	HCRYPTHASH saltHash;
	DWORD saltLen = 16;
	BYTE* saltData = new BYTE[saltLen]();
	LPWSTR filename = new WCHAR[lstrlenW(file_data->cFileName) + 1]();
	DWORD byte_path_len = lstrlenW(file_data->cFileName) * 2 + 1;
	LPSTR byte_path = new CHAR[byte_path_len]();
	StrCpyW(filename, file_data->cFileName);
	CharLower(filename);
	WideCharToMultiByte(0, 0, filename, lstrlenW(filename), byte_path, byte_path_len, 0, 0);

	CryptCreateHash(ctx->hProv, CALG_MD5, 0, 0, &saltHash);
	CryptHashData(saltHash, (BYTE*)byte_path, strlen(byte_path), 0);
	CryptGetHashParam(saltHash, KP_SALT, (BYTE*)saltData, &saltLen, 0);
	CryptSetKeyParam(ctx->hKey, KP_IV, (BYTE*)saltData, 0);

	DWORD numRead = 0x4000 - 0x4000u % ctx->blockLength;
	DWORD bytesRead = 0;
	BYTE* buffer = new BYTE[numRead + 1]();
	while (ReadFile(hRead, buffer, numRead, &bytesRead, NULL) != 0)
	{
		if (bytesRead == 0) break;
		BOOL fin = FALSE;
		if (bytesRead < numRead) fin = TRUE;
		CryptDecrypt(ctx->hKey, 0, fin, 0, buffer, &bytesRead);
		WriteFile(hWrite, buffer, bytesRead, &bytesRead, NULL);
	}
	CloseHandle(hRead);
	CloseHandle(hWrite);
}

void recursive_search(LPWSTR path, DudeCryptContext* context) {
	WIN32_FIND_DATA file_data;
	HANDLE find;
	LPWSTR star_path = cat_strings(path, (LPWSTR)L"*");

	find = FindFirstFile(star_path, &file_data);
	if (find != INVALID_HANDLE_VALUE) {
		do {
			if (lstrcmpW(file_data.cFileName, L".") == 0 || lstrcmpW(file_data.cFileName, L"..") == 0) {
				//FindNextFile(find, &file_data);
				continue;
			}

			if (file_data.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY) {
				LPWSTR new_path = cat_strings(path, file_data.cFileName, (LPWSTR) L"\\");
				recursive_search(new_path, context);
				delete new_path;
			}
			else {
				process_file(&find, &file_data, path, context);
			}
		} while (FindNextFile(find, &file_data));
	}
	if (find != INVALID_HANDLE_VALUE) FindClose(find);
}



int main(int argc, char** argv)
{
	USES_CONVERSION;

	if (argc != 2) {
		printf("Directory path required");
		return 0;
	}

	DudeCryptContext context;
	CHAR encryption_key[] = "thosefilesreallytiedthefoldertogether";

	CryptAcquireContext(&context.hProv, 0, 0, PROV_RSA_AES, 0);
	CryptCreateHash(context.hProv, CALG_SHA1, 0, 0, &context.phHash);
	CryptHashData(context.phHash, (BYTE*)encryption_key, strlen(encryption_key), 0);
	CryptDeriveKey(context.hProv, CALG_AES_256, context.phHash, CRYPT_EXPORTABLE, &context.hKey);

	DWORD key_mode = 1;
	CryptSetKeyParam(context.hKey, KP_MODE, (BYTE*)&key_mode, 0);
	CryptGetKeyParam(context.hKey, KP_BLOCKLEN, (BYTE*)&context.blockLength, &context.blockLengthLen, 0);
	context.blockLength /= CHAR_BIT;

	LPWSTR starting_path = A2W(argv[1]);
	recursive_search(starting_path, &context);
    return 0;
}

