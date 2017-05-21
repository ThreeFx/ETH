int abs_diff(int i, int j) {
    int k = i - j;
    if (k < 0)
        k -= k;
    return k;
}

int sad(int left_image[], int right_image[], int image_size) {
    int result = 0;

    for (int i = 0; i < image_size; i++) {
        result += abs_diff(left_image[i], right_image[i]);
    }

    return result;
}

int main() {
    int image_size = 9;
    int left_image[9] = {5, 16, 7, 1, 1, 13, 2, 8, 10};
    int right_image [9] = {4, 15, 8, 0, 2, 12, 3, 7, 11};
    return sad(left_image, right_image, image_size);
}
