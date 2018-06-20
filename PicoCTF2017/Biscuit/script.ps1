$cookieLocation = 'C:\Users\John\AppData\Local\Google\Chrome\User Data\Default\cookies'
$tempFileName = [System.IO.Path]::GetTempFileName()

"select writefile('$tempFileName', encrypted_value) from cookies where host_key = 'localhost' and path = '/api' and name = 'sessionId';" | sqlite3.exe "$cookieLocation"
$cookieAsEncryptedBytes = Get-Content -Encoding Byte "$tempFileName"
Remove-Item "$tempFileName"

Add-Type -AssemblyName System.Security
$cookieAsBytes = [System.Security.Cryptography.ProtectedData]::Unprotect($cookieAsEncryptedBytes, $null, [System.Security.Cryptography.DataProtectionScope]::CurrentUser)
$cookie = [System.Text.Encoding]::ASCII.GetString($cookieAsBytes)
$cookie