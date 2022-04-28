
leftNum = 0
finished = False
with open('times.txt', 'w') as f:
    while(leftNum <= 10140): 
        f.write(str(leftNum))
        f.write('-')
        leftNum+=150
        f.write(str(leftNum))
        f.write('\n')
        
