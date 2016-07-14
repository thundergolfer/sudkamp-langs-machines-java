# CYK

### Sudkamp 3<sup>rd</sup> Edition

#### Algorithm 4.6.1

**input:**
context-free grammar G = (V, &sum;, P, S), string *u* = x<sub>1</sub>x<sub>2</sub>...x<sub>n</sub>, where u is element of &sum;*

1. initialize all X<sub>i,j</sub> to NULL set
2. **for** i = 1 to *n*
  * **for** each variable A, if there is a rule A &rarr; x<sub>i</sub> then X<sub>i,i</sub> := X<sub>i,i</sub> UNION {A}
3. **for** *step* = 2 to *n*
  1. **for** k = *i* to *i* + *step* - 2
    * if there are variables B *elemOf* X<sub>i,k</sub>, C *elemOf* X<sub>k+1, i+*step*-1</sub>, and a rule A &rarr; BC, then X<sub>i,i+*step*-1</sub> := X<sub>i,i+*step*-1</sub> UNION {A}
4. *u* is element of L(G) if S *elemOf* X<sub>i,*n*</sub>
