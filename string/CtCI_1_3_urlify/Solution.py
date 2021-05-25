# URLify: Write a method to replace all spaces in a string with '%20' You may assume that the string
# has sufficient space at the end to hold the additional characters, and that you are given the "true"
# length of the string. (Note: if implementing in Java, please use a character array so that you can
# perform this operation in place.)

def urlify(str):
    builder = []
    whitespace = False
    for char in str:
        if char == ' ':
            if len(builder) > 0:
                whitespace = True
        else:
            if whitespace == True:
                builder.append('%20')
                whitespace = False
            builder.append(char)

    return "".join(builder)


print(urlify("  Hello  my world  "))



        
