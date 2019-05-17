// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

(LOOP)

	// i = 8191 counter
	@ 8191
	D = A
 	@ i
 	M = D

 	// D = KBD
	@ KBD
	D = M

	// if KBD > 0 blacken screen
	@ BLACKEN
	D; JGT

	// if KBD = 0 whiten screen
	@ WHITEN
	D; JEQ

	@ LOOP
	0; JMP

(BLACKEN)

	// from SCREEN + 8191 to SCREEN

	@ i
	D = M

    @ SCREEN
    A = A + D
    M = -1

    @ i 
    M = M - 1
    D = M

 	@ BLACKEN
 	D; JGE

	@ LOOP
	0; JMP

(WHITEN)

	// from SCREEN + 8191 to SCREEN

	@ i
	D = M

    @ SCREEN
    A = A + D
    M = 0

    @ i 
    M = M - 1
    D = M

 	@ WHITEN
 	D; JGE

	@ LOOP
	0; JMP