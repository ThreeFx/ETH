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
    xor $t4, $t4, $t4

    addi $t4, t1, -1
    multu $t1, $t1, $t4
    srl $t1, $t1, 1

    xor $t4, $t4, $t4
    addi $t4, t2, 1
    multu $t2, $t2, $t4
    srl $t2, $t2, 1

    sub $s0, $t2, $t1

# new: 14 / 20Mhz = 7 * 10e-7s

# OLD:
# 9 + 4 * (B - A) / 20 MHz -> (9 + 4 * (B - A)) / 20'000'000 s

