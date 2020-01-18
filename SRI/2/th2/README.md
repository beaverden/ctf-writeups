Open the kibana panel, change the date to 2018 (to see the events)

You will find ~14000 randomly generated events, somewhere there hides a genuine one.

Let's try to isolate them using their fake host names

```
NOT host: "media-for-the-masses.theacademyofperformingartsandscience.org"
NOT host: "api.facebook.com"
NOT host: "cdn.theacademyofperformingartsandscience.org"
NOT host: "motion-media.theacademyofperformingartsandscience.org"
NOT host: "theacademyofperformingartsandscience.org"
```

Now we see the event with the host `CYBERINT.RO` that is the answer