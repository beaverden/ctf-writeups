#include "pch.h"
#include <stdio.h>
#include <math.h>
#include <Windows.h>
#include <time.h>
#include <algorithm>
#include <thread>
#include <string>
#include <vector>

#define N 256   // 2^8

void swap(unsigned char* a, unsigned char* b) {
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

int RC4(unsigned char* key, int keylen, unsigned char* plaintext, int len, unsigned char* ciphertext) {

	unsigned char S[N];
	int j = 0, i = 0;
	for (int i = 0; i < N; i++) S[i] = i;

	for (int i = 0; i < N; i++) {
		j = (j + S[i] + key[i % keylen]) % N;
		int tmp = S[i];
		S[i] = S[j];
		S[j] = tmp;
	}

	i = 0;
	j = 0;

	for (int n = 0; n < len; n++) {
		i = (i + 1) % N;
		j = (j + S[i]) % N;
		int tmp = S[i];
		S[i] = S[j];
		S[j] = tmp;
		ciphertext[n] = S[(S[i] + S[j]) % N] ^ plaintext[n];
	}
	return 0;
}

void task(int id, unsigned int min, unsigned int max)
{
	FILE* f;
	std::string path = "D:\\Google Drive\\Programming\\CTF\\FbCTF2019\\matryoska\\result.bin";
	path += std::to_string(id);
	fopen_s(&f, path.c_str(), "wb");
	for (unsigned int i = min; i < max; i++)
	{
		if (i % 10000000 == 0) printf("%u\n", i);
		unsigned char key[] = { 0x00, 0x00, 0x00, 0x00, 0x77, 0x54, 0x39, 0x36 };
		*(unsigned int*)(key) = i;
		unsigned char src[] = { 0xF6,0x2C,0x72,0x1A,0x03,0x99,0x0E,0x78,0xBD,0x90,0xE9,0x68,0xD0,0x69,0x37,0x29,0xF8,0x12,0xF4,0xE5,0xD0,0xFB,0xF3,0x7E,0x72,0x61,0x79,0x19,0xED,0x44,0x12,0x52,0xF5,0xF9,0xAA,0x14,0x36,0x0D,0x1F,0xB2,0x52,0x6B,0xF2,0x6A,0xDA,0x9D,0xEC,0x3C };
		RC4(key, 8, src, 48, src);
		bool pr = true;
		for (int j = 8; j < 28; j++)
		{
			if (isprint(src[j]) == 0) {
				pr = false; break;
			}
		}
		if (pr)
		{
			printf("%40s\n", src + 8);
			fwrite(src, 1, 48, f);
		}
	}
	printf("%d Finished\n", id);
	fclose(f);
}

typedef unsigned long long ull;

#define MOD 59999
int main() {
	// PART 1, brute force the first 4 bytes of the key
	//int tasks = 4;
	//unsigned int part = 0xFFFFFFFF / tasks;
	//unsigned int start = 0;
	//std::vector<std::thread> v;
	//for (int i = 0; i < tasks; i++)
	//{
	//	unsigned long long max = start + part;
	//	if (max > 0xFFFFFFFF) max = 0xFFFFFFFF;
	//	std::thread t(task, i, start, max);
	//	v.push_back(std::move(t));
	//	start += part;
	//}
	//for (auto& t : v)
	//{
	//	if (t.joinable()) t.join();
	//}
	// PART 2, use the first 8 bytes from the decrypted message to get the flag
	ull l = 0x115C28DAF028DB91;
	ull a = l ^ 0x115C28DA834FEFFDLL;
	ull b = a ^ 0x665F336B1A566B19LL;
	ull c = b ^ 0x393B415F5A590044LL;
	ull d = c ^ 0x3255557376F68LL;
	char buff[128];
	ull* longBuff = (ull*)buff;
	longBuff[0] = d;
	longBuff[1] = c;
	longBuff[2] = b;
	longBuff[3] = a;
	printf("%s\n", buff);
    return 0;
};