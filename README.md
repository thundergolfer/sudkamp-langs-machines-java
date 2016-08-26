<p align="center">
  <img src="images/repo_header_java.png">
</p>
# Sudkamp Languages And Machines - Java

All the algorithms from Sudkamp's [Languages and Machines](http://www.amazon.com/Languages-Machines-Introduction-Computer-Science/dp/0321322215) implemented in Java.

## Contributing
#### Completion Status :    **25 %**

You are welcome to fork this repo in order to complete unimplemented algorithms or to improve on the existing ones. Please try to stick as close to the pseudo-code from the book.

### Index of Algorithms

| **Page** | **Number** | **Name (in 3<sup>rd</sup> edition)** | **Name (in repository)** | **File**   |
|:----------|:-----------|:-------------------------------------|:-------------------------|:-----------|
| 108       | 4.2.1      | Construction of Set of Nullable Vars | nullableVarsSet()        | [ContextFreeGrammar.java](src\main\java\grammars\ContextFreeGrammar.java) |
| 114	    | 4.3.1      | Construction of the Set CHAIN(A)     | chain( A ) | [ContextFreeGrammar.java](src\main\java\grammars\ContextFreeGrammar.java)|
| 117       | 4.4.2      | Constr. of Set of Vars that Derive Terminal Strings | constructSetOfVarsThatDeriveTerminalStrings() | [ContextFreeGrammar.java](src\main\java\grammars\ContextFreeGrammar.java)|
| 119       | 4.4.4      | Contruction of Set of Reachable Vars. | constructSetofReachableVars() | [ContextFreeGrammar.java](src\main\java\grammars\ContextFreeGrammar.java)|
| 126       | 4.6.1      | CYK Algorithm | CYK.testMembership() | [CYK.java](src\main\java\parsing\CYK.java)|
| 172       | 5.6.3      | Contruction of DM, a DFA Equiv. to NFA M | | |
| 179       | 5.7.2      | Determination of Equivalent States of DFA | | |
| 194       | 6.2.2      | Construction of a Regular Expression from a Finite Automaton | constructFromFiniteAutomaton() | [RegExp.java](src\main\java\RegExp.java)|
| 543       | 17.4.3     | Recursive Simulation of NonDeterministic Turing Machine | | |
| 558       | 18.2.1     | Breadth-First Top-Down Parser | | |
| 564       | 18.4.1     | Breadth-First Bottom-Up Parser |  | |
| 581       | 19.4.1     | Construction of FIRST<sub>k</sup> Sets | | |
| 583       | 19.5.1     | Construction of FOLLOW<sub>k</sup> Sets | | |
| 588       | 19.7.1     | Deterministic Parser for a Strong LL(k) Grammar | | |
| 591       | 19.8.3     | Deterministic Parser for an LL(k) Grammar | | |
| 600       | 20.2.1     | Parser for an LR(0) Grammar | | |
| 604       | 20.3.3     | Parser Utilizing the Deterministic LR(0) Machine | | |
| 618       | 20.5.4     | Parser for an LR(1) Grammar |  | |


### Index Of Related Algorithms Not Provided In The Textbook

| **Name**                      | **Name (In Repository)**        | **File**         |
|:-------------------------------|:---------------------------------|:---------------|
| Elimination of Chain Rules     | removeChainRules()               | [ContextFreeGrammar.java](src\main\java\grammars\ContextFreeGrammar.java) |
| Remove Recursive Start Symbol  | removeRecursiveStartSymbol()     | [ContextFreeGrammar.java](src\main\java\grammars\ContextFreeGrammar.java) |

## License

[![CC0](http://i.creativecommons.org/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

To the extent possible under law, [Thundergolfer](http://www.jonathonbelotti.com) has waived all copyright and related or neighboring rights to this work.
