# gameoflife
Game of Life - webapp

It is a Java 8 Spring webapplication.

Given an infinite plane with cells.

- In my solution it is a 100 x 100 board where the last row is adjacent to the first, the last column is adjacent to the first.

Every cell is dead or alive and living cells have color (green or blue).

In every moment the cells have a transition simultaneously according to these rules:

• Any live cell with fewer than two live neighbours dies, as if caused by under-population.
• Any live cell with two or three live neighbours lives on to the next generation.
• Any live cell with more than three live neighbours dies, as if by over-population.
• Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

In the webapp the user can do the following actions:

- Start a new simulation.
- Step to the next cycle.
- Step back to the previous cycle (only once).

You can download a ready-to-deploy package from here: 

https://1drv.ms/u/s!AiAVRcFeliU3jKw7eZB_NhKP9IRzCw