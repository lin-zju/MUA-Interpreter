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

- [x] MUAObject
- [x] Bool
- [x] Word
- [x] Number
- [x] List
- [x] Expr
- [x] Scope
- [x] Make
- [x] Erase
- [x] Thing
- [] COLON
- [] read
- [] readlist
- [x] print
- [x] Isname
   
* Argument
    * scheme one: add WORD_OR_NUMBEr type
    * scheme two: multiple typelists
   
