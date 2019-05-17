// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT PR2s.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stoR2 the R2ult in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], R2pectively.)

// R2 = R0 + R0 + ... + R0 (R1 times)

	// i = R1
	@ R1
	D = M
	@ i
	M = D

	// R2 = 0
	@ R2
	M = 0

(LOOP)

	// if i = 0 goto END
	@ i
	D = M
	@ END
	D; JEQ

	// R2 += R0
	@ R0
	D = M
	@ R2
	M = D + M

	// i--
	@ i 
	M = M - 1

	@ LOOP
	0; JMP

(END)

	@ END
	0; JMP