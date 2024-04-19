# User manual

## Playing on the command line

Clone this Git repository to your own computer.

Run the PerformanceTest.java file in:
...\src\main\java\datastructureproject\PerformanceTest.java

Make your moves by typing the starting square and target square of your move (a2a3 or h2h4).
You will automatically play as white.

If you want to use a graphical user interface follow the instructions below.

## XBoard Setup
XBoard is a graphical user interface chessboard for chess engines. Xboard can be connected to your tira chess engine.

1. Download the tar.gz file of the latest stable version of [XBoard](https://www.gnu.org/software/xboard/#download)

Uncompress it, for example, under the same directory where the chess directory is.

    $ tar xvzf xboard-4.9.0.tar.gz

2. Start XBoard:

    $ xboard

3. Make sure that you have a jar file for your engine:

    $ ./gradlew build

4. Under Engine tab, select Edit Engine List..

5. Add the path of the chess engine's jar file in the list

    "tira-chess" -fcp "java -jar /home/local/ ..your path.. /chess/build/libs/chess-all.jar"

You can replace "tira-chess" with any unique name.
Then click 'commit changes' and 'OK'.

6. Under Engine tab, select Load New 1st Engine..

Just select your engine from the list and click 'OK'.

7. Make your first move (as white) and your engine should respond with its move.

You can also select 'Machine White' under the Mode tab. In this case, your engine will play white pieces and make its move first.

With the "Two Machines" mode, you can also get two bots playing against each other.

**Note:** How to start a new XBoard game after one has ended? First under Edit, select New Game. Then just select the Mode.
