# -*- coding: utf-8 -*-
"""
Created on Tue Jan 26 09:28:11 2021

@author: 21yiqiang
"""

import pygame
pygame.font.init()

#Set up the drawing window
screen = pygame.display.set_mode([500, 500])

x=250
y=250
vel = 0.3
arial = pygame.font.SysFont("Arial", 35)

#Run until the user asks to quit
running = True
while running:
        
    #Did the user click the window close button?
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    #white background
    screen.fill((255, 255, 255))
    keys = pygame.key.get_pressed()
    if keys[pygame.K_LEFT] and x>0:
        x-=vel
    if keys[pygame.K_RIGHT] and x<500:
        x+=vel
    if keys[pygame.K_UP] and y>0:
        y-=vel
    if keys[pygame.K_DOWN] and y<500:
        y+=vel
        
     #Draw a solid blue circle in the center
    pygame.draw.circle(screen, (0, 0, 255), (x, y), 75) 
    #display text: https://www.youtube.com/watch?v=mfRnTDJbM68&ab_channel=PaulBaumgarten
    label = arial.render("Use arrow keys to move the blue circle.", 1, (0,0,0))
    screen.blit(label, (0,0))
    
    # Flip the display. Without this, nothing would appear on the display
    pygame.display.flip()

pygame.quit()   