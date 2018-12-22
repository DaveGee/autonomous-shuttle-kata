A quick TDD implementation of the "autonomous shuttle kata", which is a
variation of the _mars rover kata_ more adapted to my current employer's industry

## Rules/Tips

* TDD only
* Pair programming if possible
* Design decisions and interpretations up to the pair/dev
* Priority to features over edge cases to go fast, or vice-versa if you have more time

# Autonomous shuttles kata instructions

You are building a new autonomous shuttle and are implementing the system that
will interface with the fleet's dispatch service. You need to receive and handle
missions, and report the position of the shuttle

Your new shuttle is very simple (simple is always better!) and sees the world
as a 2D grid. The shuttle reports its position as a coordinate on this grid (x, y),
and its bearing as a string corresponding to the 4 cardinal directions (N, S, W, E)

The mission coming from the central dispatch are simple strings,
containing a sequence of 4 possible letters that will tell the shuttle what to
do next (`F` - forward, `B` - backward, `L` - turn left, `R` - turn right)

Your shuttle is also armed with sensors and it detects obstacles before moving.
In case it detects one it stays safe and doesn't move :
it aborts the current mission, stays where it is and reports the obstacle(s).

