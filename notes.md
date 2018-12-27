# Desing

## Parser

Outside a list:
* If a function, or expression keyword is encountered:
    * eval using all elements in arglist, add to arglist
* If a statement is encountered:
    * exec using all elements in arglist
    * If there are still tokens, issue an error
    
Inside a list
* If a function, or expression keyword is encountered:
    * eval using needed elements in arglist, add to arglist
    * remove corresponding args
* If a statement is encountered:
    * construct using needed args
    * add the statement object to the list

## Major Classes

* Scope
* Variable
    * Number
    * Word
    * List
* Statement
* Expression
    * Literal
    * Function
    * Expression
    
## Statements and functions/expressions


All objects:

* Number
* Word
* List
* Bool
* Expression
    * Make
    * Erase
    * Read
    * Readlist
    * Thing
    * Isname
    * print
    * repeat

Statements:

* `make`
* `erase`
* `print`
* `expr`
* `repeat`

Functions/expressions

* `thing`
* `:word`
* `read`
* `readlist`
* all operators

## How do you process a statement?

* Parser: parse a statement into its component

* If keyword
    * construct Expr using num args
* If func
    * construct Func using num args
* Otherwise
    * return the object


* Statement: Exec: scope
* Expression: Eval: scope

# TODO

The structure of everything is:

* (optional) Keyword + a list of expressions

- [] MUAObject
- [] Bool
- [] Word
- [] Number
- [] List
- [] Expr
- [] Scope
- [] Make
- [] Erase
- [] Thing
- [] COLON
- [] read
- [] readlist
- [] print
- [] Isname
   
* Argument
    * scheme one: add WORD_OR_NUMBEr type
    * scheme two: multiple typelists
    
    
* Expr
    * arglist
    * SubType
    * getName
    * toString


* list   
    * save tokens only?

```
make "fib [
[n]
[
    if eq :n 1
    [
        output 1
        stop
    ]
    []
    output mul :n fib sub :n 1
]
]      
```