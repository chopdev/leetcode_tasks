# String Compression: Implement a method to perform basic string compression using the counts
# of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
# "compressed" string would not become smaller than the original string, your method should return
# the original string. You can assume the string has only uppercase and lowercase letters (a - z).

# aa -> aa
#abc -> abc
# aabbcc -> aabbcc
# aabbccc -> a2b2c3

def compress(str):
    if not str or len(str) == 1:
        return str

    i = 0
    builder = []
    count = 1
    while i < len(str) - 1:
        if str[i] != str[i+1]:
            builder.append(str[i])
            builder.append(f'{count}')
            count = 1
        else:
            count += 1
        i+= 1

    # handle last character
    last = len(str) - 1
    if (str[last] == str[last - 1]):
        count += 1
    builder.append(str[last])
    builder.append(f'{count}')

    compressed = "".join(builder)
    return compressed if len(compressed) < len(str) else str


print(compress(""))
print(compress("a"))
print(compress("aa"))
print(compress("abc"))
print(compress("aabbcc"))
print(compress("aabbccc"))
print(compress("aaabbcccaa"))