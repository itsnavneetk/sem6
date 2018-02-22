#Python 3
#Incomplete code!
#outputs only the sets of the third iteration
from itertools import zip_longest

import itertools

support = 0.42
confidence = 0.8

f = open('mushrooms.csv', 'r')
transaction = []
lineCount = 0

#extracing transactions
for line in f:
    splitLine = line.split(",")
    splitLine[-1] = splitLine[-1][:1]
    transaction.append(splitLine)
    lineCount = lineCount + 1

print("No of transactions : "+str(lineCount))
print(transaction)

#functions
def countFirst(transaction):
    tempDict = {}
    for t in transaction:
        for item in t:
            if item in tempDict:
                tempDict[item] += 1
            else:
                tempDict[item] = 1
    return tempDict

def findFrequency(c1, support, lineCount):
    tempDict = {}
    for key in c1:
        if(float(c1[key]/lineCount) >= support):
            tempDict[key] = c1[key]
    return tempDict

def candidateGen(keys):
    tempDict={}
    for i in keys:
        for j in keys:
            if i!=j and (j,i) not in tempDict:
                tempDict[tuple([min(i,j),max(i,j)])] = 0
    return tempDict

def candidateGen2(keys, size):
    tempDict={}
    tempList=[]
    for key in keys:
        for k in key:
            if k not in tempList:
                tempList.append(k)
    newList =[]
    for i in (list(itertools.combinations(tempList, size))):
        if i not in newList:
            newList.append(i)
    for i in newList:
            tempDict[i] =0
    return tempDict

def addFreq(c, transactions):
    for key in c:
        for t in transactions:
            if key[0] in t and key[1] in t:
                c[key] += 1
    return c
def addFreq3(c, transactions):
    for key in c:
        for t in transactions:
            if key[0] in t and key[1] and key[2] in t:
                c[key] += 1
    return c
def addFreq4(c, transactions):
    for key in c:
        for t in transactions:
            if key[0] in t and key[1] and key[2] and key[3] in t:
                c[key] += 1
    return c
#first iteration
c1 = countFirst(transaction)
f1 = findFrequency(c1, support, lineCount)

print("\nL1")
print(f1)

#second iteration
c2 = candidateGen(f1.keys())
c2 = addFreq(c2, transaction)
f2 = findFrequency(c2, support, lineCount)
print("\nL2")
print(f2)


#third iteration
print("\nL3")
c3 = candidateGen2(f2.keys(), 3)
c3 = addFreq3(c3, transaction)
f3 = findFrequency(c3, support, lineCount)

print(f3)

#fourth iteration
print("\nL4")
c4 = candidateGen2(f3.keys(), 4)
c4 = addFreq4(c4, transaction)
f4 = findFrequency(c4, support, lineCount)
print(f4)
