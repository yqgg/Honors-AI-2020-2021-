# -*- coding: utf-8 -*-
"""
Created on Thu Jan 28 09:31:14 2021

@author: 21YiQianG
"""

import pyglet

window = pyglet.window.Window(1280, 720)

pic = pyglet.image.load('circle.png')
sprite = pyglet.sprite.Sprite(pic, 0, window.height/2)

#https://www.youtube.com/watch?v=mSB4a_x0DmQ&ab_channel=EventHandler
@window.event
def on_draw():
    window.clear()
    sprite.draw()

def update(dt):
    sprite.x += 1
            
pyglet.clock.schedule_interval(update, 1/60)        
pyglet.app.run()