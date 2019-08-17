states = {}
queue = []

states[(0, 0, 0)] = ''
queue.append((0, 0, 0))

while not len(queue) == 0:
    state = queue[0]
    queue = queue[1:]

    m, h, c = state
    s = states[state]
    if m == 72 and h == 30 and c == 0:
        print(s)
        exit(0)

    # play 
    play = (m - 2, h + 4, c - 1)
    if not play in states:
        states[play] = s + 'play,'
        queue.append(play)
    cl = (m, h - 1, c + 6)
    if not cl in states:
        states[cl] = s + 'clean,'
        queue.append(cl)
    f = (m + 10, h + 2, c - 1)
    if not f in states:
        states[f] = s + 'feed,'
        queue.append(f)     
