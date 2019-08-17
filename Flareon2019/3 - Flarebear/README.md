# Flarebear

The game is simple, the bear has 3 actions.

- play
- feed
- clean

Each action has its own consequences on the bear's happy state, mass and cleanliness

- play (mass - 2, happy + 4, clean - 1)
- feed (mass + 10, happy + 2, clean - 1)
- clean (mass + 0, happy - 1, clean + 6)

Based on that, we need to make the bear especially happy.

After decompiling the APK, we look at the source code and find the file `FlareBearActivity.java` where all the action is happening.

We find this interesting piece of code

```
            if (isEcstatic()) {
                danceWithFlag();
                return;
            }
```

What this means is the the bear needs to be `ecstatic`, extra happy for us to see the flag, the seems to be `AES` encrypted in two separate files.

The `isEcstatic()` function looks like that

```
    public final boolean isEcstatic() {
        int state = getState("mass", 0);
        int state2 = getState("happy", 0);
        int state3 = getState("clean", 0);
        if (state == 72 && state2 == 30 && state3 == 0) {
            return true;
        }
        return false;
    }
```

Which means that the bear has to weigh 72 units, has to be exactly 30 units of happy and be perfectly clean.

As we start with `0, 0, 0` values - it is clear that we need to find the right combination of actions, that bring the bear to the ecstatic state.

This can be done in many was, including editing preferences thus altering the saved state of the bear. 

Or by finding the right sequence with `DFS` of `BFS`. I chose `BFS`

The algorithm is quite straightforward, start from the state `(0, 0, 0)` and branch to another state, for each possible action.
As each action changes the state, we should (probably) arrive at the state `(72, 30, 0)` sooner or later. 

And we did! The correct action sequence is `play,play,play,play,clean,clean,feed,feed,feed,feed,feed,feed,feed,feed`

After applying this set of actions, we get the dancing bear and the flag `th4t_was_be4rly_a_chall3nge@flare-on.com`	