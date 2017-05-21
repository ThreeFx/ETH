#
# Sum of Absolute Differences Algorithm
#
# Authors:
#	X Y, Z Q
#
# Group: ...
#

.text


main:


# Initializing data in memory...
# Store in $s0 the address of the first element in memory
	# lui sets the upper 16 bits of thte specified register
	# ori the lower ones
	# (to be precise, lui also sets the lower 16 bits to 0, ori ORs it with the given immediate)
	     lui     $s0, 0x1001 # Address of first element in the vector
	     ori     $s0, 0x0000
	     addi   $t0, $0, 5	# left_image[0]
	     sw      $t0, 0($s0)
	     addi   $t0, $0, 16	# left_image[1]
	     sw      $t0, 4($s0)
	     addi   $t0, $0, 7	# left_image[2]
	     sw      $t0, 8($s0)
	     addi   $t0, $0, 1	# left_image[3]
	     sw      $t0, 12($s0)
	     addi   $t0, $0, 1	# left_image[4]
	     sw      $t0, 16($s0)
	     addi   $t0, $0, 13	# left_image[5]
	     sw      $t0, 20($s0)
	     addi   $t0, $0, 2	# left_image[6]
	     sw      $t0, 24($s0)
	     addi   $t0, $0, 8	# left_image[7]
	     sw      $t0, 28($s0)
	     addi   $t0, $0, 10	# left_image[8]
	     sw      $t0, 32($s0)

             lui     $s1, 0x1001
	     ori     $s1, 0x0024
	     addi   $t0, $0, 4	# left_image[0]
	     sw      $t0, 0($s1)
	     addi   $t0, $0, 15	# left_image[1]
	     sw      $t0, 4($s1)
	     addi   $t0, $0, 8	# left_image[2]
	     sw      $t0, 8($s1)
	     addi   $t0, $0, 0	# left_image[3]
	     sw      $t0, 12($s1)
	     addi   $t0, $0, 2	# left_image[4]
	     sw      $t0, 16($s1)
	     addi   $t0, $0, 12	# left_image[5]
	     sw      $t0, 20($s1)
	     addi   $t0, $0, 3	# left_image[6]
	     sw      $t0, 24($s1)
	     addi   $t0, $0, 7	# left_image[7]
	     sw      $t0, 28($s1)
	     addi   $t0, $0, 11	# left_image[8]
	     sw      $t0, 32($s1)

	# TODO4: Loop over the elements of left_image and right_image

	addi $s3, $0, 0 # $s1 = i = 0
	addi $s4, $0, 9	# $s2 = image_size = 9

    xor $t2, $t2, $t2

loop:
	# Check if we have traverse all the elements
	# of the loop. If so, jump to end_loop:
    beq $s3, $s4, end_loop

	# Load left_image{i} and put the value in the corresponding register
	# before doing the function call
    lw $t0, 0($s0)
    lw $t1, 0($s1)

	# Call abs_diff
    j abs_diff

ret_abs:
    add $t2, $t2, $t3

    addi $s0, $s0, 4
    addi $s1, $s1, 4
    addi $s3, $s3, 1

j loop

end_loop:

    # Fuck this

	#TODO5: Call recursive_sum and store the result in $t2
	#Calculate the base address of sad_array (first argument
	#of the function call)and store in the corresponding register

	# ...

	# Prepare the second argument of the function call: the size of the array

	#.....

	# Call to funtion

	# ....


	#Store the returned value in $t2

	# .....


end:
	j	end	# Infinite loop at the end of the program.




# TODO2: Implement the abs_diff routine here, or use the one provided

abs_diff:
    xor $t3, $t3, $t3
    sub $t3, $t0, $t1
    slt $t4, $t3, $zero
    beq $t4, $zero, end_abs
    sub $t3, $zero, $t3
end_abs:
    j ret_abs

# TODO3: Implement the recursive_sum routine here, or use the one provided
# No
