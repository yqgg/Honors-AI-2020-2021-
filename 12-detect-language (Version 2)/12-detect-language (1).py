# -*- coding: utf-8 -*-
"""
Created on Thu Jan 14 09:54:44 2021

@author: 21yiqiang
"""
import string
with open ('letter frequency.csv', encoding = "utf-8") as myfile:
    text = myfile.readlines()
    
data = []
for line in text:
    data.append(line.strip().split(","))
#print(data)
        
letters = []
for r in range(1, len(data)):
    letters.append(data[r][0])
#print(letters)
    
languages = []
for c in range(1, len(data[0])):
    languages.append(data[0][c])
#print(languages)

#Create dictionary of wikipedia's letter frequency table
#{'English': {'a': 0.0816, 'b': ...}, 'French': {'a': 0.07636, 'b': ...} ...}
d = {}
for lang in languages:    
    letterFreq = {}
    d[lang] = letterFreq
    n = 1
    for letter in letters:
        letterFreq[letter] = data[n][languages.index(lang)+1]
        n = n+1
#print(d)

s = input("Enter text to detect language: ")
#Remove punctuation: https://careerkarma.com/blog/python-remove-punctuation/
s = s.translate(str.maketrans('','',string.punctuation))
s = s.replace("\n", "") 
s = s.replace(" ", "")
#print("\n" + s)

#Letter frequency of entered text
txtFreq = {}
for char in s:
    if char in letters:
        txtFreq[char] = txtFreq.get(char,0) + 1
total = len(s)
for char in txtFreq:
    txtFreq[char] = round(txtFreq[char]/total, 4)
#print("\nLetter frequency in entered text: ")
#print(txtFreq)

#Comparing letter frequency of entered text to wikipedia's frequency table
#Euclidean Distance: https://machinelearningmastery.com/distance-measures-for-machine-learning/
euc = {}
for lang in d:
    sum = 0.0
    x = d[lang]
    for char in x:
        a = float(x.get(char))
        b = float(txtFreq.get(char,0))
        sum += ((a-b)**2)**(1/2)
        euc[lang] = sum
        #print(sum)
        
minimum = 100
detectedLang = ""
for lang in euc:
    if euc[lang] < minimum:
        minimum = euc[lang]
        detectedLang = lang
print("\nDetected language: " + detectedLang)


