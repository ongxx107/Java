The   Game Premise
PlanetWars is a game where two   players   are   pitted   against   each   other   in   a   system   of interconnected
planets.   The   players   each   start   out   on   a   home   planet   and   the   object   of   the   game is   to   
expand   and   eventually   take   over   all   of   the   opponent’s   planets.

DIRECTION: My myStrategy.java and myGraph.java which are store in src/planetwars/strategies directory are being compressed 
in jar file named final_project_4.jar in /strategies directory.  
To test run Driver.java or StrategyRanker.java in src/planetwars/publicapi directory.

Game   Board
The   game   board   is   set   up   as   a   graph   of   planets   interconnected   by   weighted   edges.
● Population   growth   occurs   at   a   set   rate   on   each   planet.
● If   two   planets   are   connected   by   an   edge,   then   shuttles   can   be   sent   between   these
planets.   The   distance   between   the   planets   affects   how   many   turns   it   takes   for   shuttles   to
move   from   one   planet   to   the   other.
● The   planets   vary   by   two   factors:
    ○ Size   correlates   to   the   total   population   a   planet   can   support.   Once   a   planet   hits   
    its maximum   population,   population   growth   will   cease   and   any   population   that exceeds   the   
    maximum   (for   example,   from   incoming   shuttles)   will   decrease   by   the rate   described   below.
    ○ Habitability   correlates   to   the   population   growth   rate   on   a   planet.   Population change   
    after   one   turn   for   a   given   planet   is   defined   below.
        ■ Let    c    =   current   population,    m    =   max   population,    g    =   growth   rate,   and    p    = overpopulation   penalty.
            ● If    c   <   m ,   pop   next   turn   =  c   *g
            ● If    c   >=   m ,   pop   next   turn   =  c      -   (c-m)*p
        ■ Currently,    p   =    0.1   and  g      =    1   +   (habitability   /   100)
● Each   player   starts   out   with   one   planet   with   a   given   population.   A   player   can   
  send shuttles   with   population   to   neighboring   planets   on   his/her   turn.
● Each   player   receives   information   about   the   entire   game   board,   including   the   
  planet   IDs of   all   planets   and   which   planets   are   interconnected.   However,   a   
  player   can   only   see detailed   information   (owner,   population,   size,   habitability,   
  incoming   shuttles)   about   the planets   that   they   own   and   their   neighboring   planets.

Game   Flow
In   one   turn,   a   player
    ● Receives   information   about   the   game   state
    ● Adds   moves   to   the   event   queue
        ○ A   ‘move’   is   sending   a   shuttle   from   one   owned   planet   to   a   
        neighboring   planet.   The player   can   set   how   much   population   is   sent   in   the   shuttle.
        ○ A   player   can   make   as   many   moves   as   he/she   wants   in   a   turn.
    ● Returns   the   event   queue   to   the   game   engine
The   game   engine   will   then   make   all   legal   moves   in   the   event   queue,   
    and   allow   a   half-step   of time   pass,   in   which
    ● Population   growth   (or   decay,   if   overpopulation   cap)   occurs   on   all   planets
    ● All   shuttles   move   half   a   step
The   game   play   then   passes   to   the   other   player.

Shuttles   and   Landings
A   shuttle   carries   some   amount   of   population   from   Planet   A   to   Planet   B.   
Let’s   say   that   Player   1 sent   the   shuttle   with   population   100,   and   the   distance   
between   the   planets   is   2.   The   shuttle takes   two   full   turn   cycles   to   arrive   at   
Planet   B,   and   during   that   time,   no   population   growth   occurs on   the   shuttle.   
There   are   several   possibilities   when   the   shuttle   arrives   at   Planet   B.
Player   1   Owns   Planet   B
All   population   belongs   to   the   same   player,   so   the   population   of   the   shuttle   is   simply   added   to   the
total   population   of   the   planet. Player   2   Owns   Planet   B
A   battle   commences.   The   defending   population   has   a   10%   advantage,   so   attacking   
a   planet with   the   same   number   of   people   as   are   currently   on   it   will   not   succeed.   
The   attackers   need 110%   of   the   planet   population   to   conquer   the   planet,   and   very   
small   attacks   will   have   no   effect on   the   population   of   the   planet.   The   outcome   is   
determined   by   the   following   rules:
    ● If    1.1   *   oldPlanetPop   >   attackingPop
        ○ newPlanetPop   =   min(oldPlanetPop,   1.1*   oldPlanetPop   -   attackingPop)
        ○ Owner   does   not   change
    ● If    1.1   *   oldPlanetPop   <   attackingPop
        ○ newPlanetPop   =   attackingPop   -   1.1   *   oldPlanetPop
        ○ Owner   changes   to   the   attacking   player
    ● If    1.1   *   oldPlanetPop   =   attackingPop
        ○ newPlanetPop   =   0
        ○ Neither   player   now   owns   the   planet
Whichever   side   wins   the   battle,   wins   the   planet,   and   stays   there   with   
the   remaining   population. In   the   unlikely   circumstances   that   both   sides   lose   
all   of   their   population   at   the   planet,   it   becomes neutral   and   is   up   
for   grabs   for   whoever   gets   a   shuttle   there   first.
Planet   B   is   Neutral
Player   1   captures   the   planet,   and   the   attacking   force   lands   successfully.   No   population   is   lost.
Shuttles   are   processed   in   an   order   which   rewards   both   defensive   assistance   
and   multiple concurrent   attacks.   All   friendly   shuttles   which   are   scheduled   
to   arrive   on   a   turn   are   landed,   and their   population   is   added   to   the   
planet’s   population.   Following   this,   the   attacks   of   all   hostile shuttles   
scheduled   to   arrive   are   coalesced   into   one   larger   attack   -   if   two   
hostile   shuttles   arrive   at the   same   time,   one   with   population   10   and   one   
with   population   15,   then   the   attacking   force   used to   compute   a   winner   has   population   25.


