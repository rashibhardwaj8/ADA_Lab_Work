# -- 0/1 KNAPSACK --

def zero_one_knapsack(capacity, weights, values):
    n = len(weights)
    dp_table = [[0 for _ in range(capacity + 1)] for _ in range(n + 1)]

    for i in range(1, n + 1):
        for w in range(capacity + 1):
            if weights[i - 1] <= w:
                include_item = values[i - 1] + dp_table[i - 1][w - weights[i - 1]]
                exclude_item = dp_table[i - 1][w]
                dp_table[i][w] = max(include_item, exclude_item)
            else:
                dp_table[i][w] = dp_table[i - 1][w]

    return dp_table[n][capacity]


# Example
weights_list = [1, 3, 4, 5]
values_list = [1, 4, 5, 7]
capacity = 7

print("0/1 Knapsack Result:", zero_one_knapsack(capacity, weights_list, values_list))


# -- FRACTIONAL KNAPSACK --

def fractional_knapsack_problem(capacity, items):
    # Sort items based on value/weight ratio
    sorted_items = sorted(items, key=lambda x: x[1] / x[0], reverse=True)

    max_value = 0.0

    for weight, value in sorted_items:
        if capacity >= weight:
            capacity -= weight
            max_value += value
        else:
            fraction = capacity / weight
            max_value += value * fraction
            break

    return max_value


# Example
item_list = [(10, 60), (20, 100), (30, 120)]
print("Fractional Knapsack Result:", fractional_knapsack_problem(50, item_list))


# -- MATRIX MULTIPLICATION --

def multiply_matrices(matA, matB):
    rows_A = len(matA)
    cols_A = len(matA[0])
    cols_B = len(matB[0])

    result_matrix = [[0 for _ in range(cols_B)] for _ in range(rows_A)]

    for i in range(rows_A):
        for j in range(cols_B):
            for k in range(cols_A):
                result_matrix[i][j] += matA[i][k] * matB[k][j]

    return result_matrix


# Example
matrix_A = [[1, 2], [3, 4]]
matrix_B = [[5, 6], [7, 8]]

print("Matrix Multiplication Result:", multiply_matrices(matrix_A, matrix_B))


# -- LONGEST COMMON SUBSEQUENCE --

def longest_common_subsequence(str1, str2):
    len1 = len(str1)
    len2 = len(str2)

    dp = [[0 for _ in range(len2 + 1)] for _ in range(len1 + 1)]

    for i in range(1, len1 + 1):
        for j in range(1, len2 + 1):
            if str1[i - 1] == str2[j - 1]:
                dp[i][j] = 1 + dp[i - 1][j - 1]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    return dp[len1][len2]


# Example
print("LCS Length:", longest_common_subsequence("ABCBDAB", "BDCAB"))
