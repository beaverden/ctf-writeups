from PIL import Image
import Queue

#####################################################
###
###  TL;DR - extract pixels, BFS the 2D array starting from the red color
###
###  https://en.wikipedia.org/wiki/Lee_algorithm
###  https://www.techiedelight.com/lee-algorithm-shortest-path-in-a-maze/
###  http://users.eecs.northwestern.edu/~haizhou/357/lec6.pdf
###
###  https://pillow.readthedocs.io/en/5.1.x/reference/Image.html#functions
###
#####################################################


WHITE_COLOR = 3
GREEN_COLOR = 1
RED_COLOR = 2

im = Image.open("input.png")
pixels = im.load()
width, height = im.size

pixel_colors = {}
cc = set()

# Read and remember pixel colors
green = None
red = None
for x in range(width):
    for y in range(height):
        color = pixels[x, y]
        cc.add(color)
        pixel_colors[(x,y)] = color
        if color == GREEN_COLOR and green == None:
            green = (x, y)
        elif color == RED_COLOR and red == None:
            red = (x, y)

print(green, red)


visited = {}
q = Queue.Queue()
start = red
q.put(start)
result = None
visited[start] = 1
print('Starting to search from -> ', red)
iteration = 0
# Start from the current position, fill the cells the BFS way (all 8 directions), until we find the right way
# Sooner or later we'll find the green pixels, even if it takes longer
while not q.empty() and result == None:
    iteration += 1
    if iteration % 1000 == 0:
        print(iteration)

    current = q.get()
    for i in range(-1, 2):
        for j in range(-1, 2):
            if i != 0 and j != 0:
                # All directions -> (-1,-1), (-1, 1) ....
                cx, cy = current
                nx, ny = cx + i, cy + j
                new = (nx, ny)
                if new in pixel_colors and not new in visited:
                    col = pixel_colors[new]
                    # white - can move
                    if col == WHITE_COLOR:
                        q.put(new)
                        visited[new] = visited[current] + 1
                    elif col == 1: # green
                        result = new
                        visited[new] = visited[current] + 1

# Start from the found position, return to the first red pixel
# by searching in the visited array for places that had one less steps than the current
# visited[result] = x  ----> visited[next] = x - 1 ---> visited[start] = 1
found = False
print(result)
while not found:
    rx, ry = result

    # Draw a thick red line along the way
    for i in range(-3, 4):
        for j in range(-3, 4):
            im.putpixel((rx+i, ry+j), RED_COLOR)

    for i in range(-1, 2):
        for j in range(-1, 2):
            im.putpixel((rx+i, ry+j), RED_COLOR)

            if i != 0 and j != 0:
                # All directions -> (-1,-1), (-1, 1) ....
                cx, cy = rx, ry
                nx, ny = cx + i, cy + j
                new = (nx, ny)
                if new in visited and visited[new] == visited[result] - 1:
                    result = new
                    if pixel_colors[result] == RED_COLOR:
                        found = True
                        break



im.save('result.png')
#print(pixels[:50000])