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
d = {}
for language in languages:    
    letterFreq = {}
    d[language] = letterFreq
    n = 1
    for letter in letters:
        letterFreq[letter] = data[n][languages.index(language)+1]
        n = n+1
#print(d)

s = input("Enter text to detect language: ")
#Remove punctuation: https://careerkarma.com/blog/python-remove-punctuation/
s = s.translate(str.maketrans('','',string.punctuation))
s = s.replace("\n", "") 
s = s.replace(" ", "")
print()
#print(s)
#print(len(s))

#Letter frequency in entered text
count = {}
for char in s:
    if char in letters:
        count[char] = count.get(char,0) + 1
total = len(s)
for char in count:
    count[char] = round(count[char]/total, 4)
print("Letter frequency in entered text: ")
print(count)

#Comparing letter frequency of entered text to wikipedia's frequency table
euc = {}
for lang in d:
    sum = 0.0
    for k in d[lang]:
        sum = sum + (float(d[lang][k]) - float(letterFreq[k]))**2
        euc[lang] = sum**(1/2)
        #print(sum)
        
minimum = euc['English']
detectedLang = ""
for lang in euc:
    if euc[lang] < minimum:
        minimum = euc[lang]
        detectedLang = lang

print("\nDetected language: " + detectedLang)


