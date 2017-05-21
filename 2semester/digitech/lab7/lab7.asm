#
# Calculate sum from A to B.
#
# Authors:
#	X Y, Z Q
#
# Group: ...
#

.text
main:
	#
	# Your code goes here...
	#
    lui $t1, 0               # 1
    ori $t1, $t1, 0x1234     # 1
    lui $t2, 0               # 1
    ori $t2, $t2, 0x5678     # 1
    xor $t3, $t3, $t3        # 1

loop:
    beq $t1, $t2, exit       # B - A + 1
    add $t3, $t3, $t1        # B - A
    addi $t1, $t1, 1         # B - A
    j loop                   # B - A
exit:

    add $t3, $t3, $t1        # 1
	# Put your sum S into register $t2
    xor $t2, $t2, $t2        # 1
    add $t2, $t2, $t3        # 1
end:
	j	end	# Infinite loop at the end of the program.


# 9 + 4 * (B - A) / 20 MHz -> (9 + 4 * (B - A)) / 20'000'000 s
