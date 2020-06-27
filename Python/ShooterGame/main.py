import pygame
import random
import math

from pygame import mixer
from datetime import date
from datetime import datetime
from pygame.locals import *


def showTheScore(tosX, tosY):
    score_screen = font_score.render("SCORE : " + str(score), True, (240, 240, 240))
    screen.blit(score_screen, (tosX, tosY))


def gameOver():
    textGameOver = gameOverFont.render("GAME OVER", True, (240, 240, 240))
    screen.blit(textGameOver, (300, 400))


def player(x, y):
    screen.blit(playerImg, (x, y))


def fire_bullet(x, y):
    global bullet_state
    bullet_state = "fire"
    # To fire from center of the player icon
    screen.blit(bulletImg, (x + 16, y + 10))


def policeman(x, y, currChaser):
    screen.blit(detachmentImg[currChaser], (x, y))


def policeCar(x, y):
    screen.blit(policeCarImg, (x, y))


def FbiBus(x, y, currChaser):
    screen.blit(hardDetachmentImg[currChaser], (x, y))


def isItReached(policeX, policeY, bulletX, bulletY):
    distance = math.sqrt((math.pow(policeX - bulletX, 2)) +
                         (math.pow(policeY - bulletY, 2)))
    if distance < 27:
        print(score)
        return True
    else:
        return False


isItPressed = True

while isItPressed:
    # Button - start the game
    startButton = (128, 116, 115)
    pygame.init()
    pygame.display.set_caption('''The persecution.'''.upper())
    screen = pygame.display.set_mode((800, 600))
    background = pygame.image.load('map_image2_fixed.jpg')
    screen.fill((71, 68, 68))
    screen.blit(background, (0, 0))
    homePageTitle = pygame.font.Font(None, 38).render("Преследването", True, (255, 255, 255))
    screen.blit(homePageTitle, (315, 100))
    homePageSubtitle = pygame.font.Font(None, 30).render("'The persecution'", True, (255, 255, 255))
    screen.blit(homePageSubtitle, (329, 140))
    homePageText1 = pygame.font.Font(None, 23).render("Играта 'Преследването' е направена с цел забавление. Основната идея е играчът(Вие) да ", True, (255, 255, 255))
    homePageText2 = pygame.font.Font(None, 23).render(" управлявате аватара си, чрез бутони, както и да изстрелва куршуми. От другата страна", True, (255, 255, 255))
    homePageText3 = pygame.font.Font(None, 23).render("на екрана ще изкачат противниците, който се приближават, а Вашата цел е да ги убиете.", True, (255, 255, 255))

    screen.blit(homePageText1, (65, 230))
    screen.blit(homePageText2, (65, 250))
    screen.blit(homePageText3, (65, 270))

    homePageContent = pygame.font.Font(None, 20).render("|Press the screen to start|", True, (255, 255, 255))
    # pygame.display.set_caption('Show Text')
    screen.blit(homePageContent, (323, 550))
    # pygame.draw.rect(screen, startButton, (320, 150, 150, 50))
    for event in pygame.event.get():
        if event.type == pygame.MOUSEBUTTONDOWN:
            pos = pygame.mouse.get_pos(startButton)
            isItPressed = False
        if event.type == pygame.QUIT:
            exit(0)

    pygame.display.update()


# Initialize the pyGame
pygame.init()

# Create the screen
screen = pygame.display.set_mode((800, 600))

# Background
background = pygame.image.load('map_image2_fixed.jpg')

# Sound
mixer.music.load('soundOfTheGame.wav')
mixer.music.play(-1)

# Title and Icon
pygame.display.set_caption('''The persecution.'''.upper())
icon = pygame.image.load('player_icon.png')
pygame.display.set_icon(icon)

# Player
playerImg = pygame.image.load('player_icon.png')
playerX = 370
playerY = 50
playerX_CHANGED = 0
# Player score - increased when shoot the chaser
score = 0
font_score = pygame.font.Font('freesansbold.ttf', 30)
textOfScoreX = 11
textOfScoreY = 11

gameOverFont = pygame.font.Font('freesansbold.ttf', 70)

# Bullet
bulletImg = pygame.image.load('bullet.png')
bulletX = 0
bulletY = 50
bulletX_CHANGED = 0
bulletY_CHANGED = 5
bullet_state = "ready"

# Detachment: Easy
detachmentImg = []
detachmentX = []
detachmentY = []
detachmentX_CHANGED = []
detachmentY_CHANGED = []
numOfChaser = 8

# Policeman
for i in range(numOfChaser):

    detachmentImg.append(pygame.image.load('policeman.png'))
    detachmentX.append(random.randint(0, 700))
    detachmentY.append(480)
    detachmentX_CHANGED.append(3)
    detachmentY_CHANGED.append(-60)


# PoliceCar
policeCarImg = pygame.image.load('police_car.png')
policeCarX = 0
policeCarY = 0
policeCarX_CHANGED = 0.3
policeCarY_CHANGED = 0

# FBI_Bus
FbiBusImg = pygame.image.load('FBI_bus.png')
FbiBusX = 0
FbiBusY = 0
FbiBusX_CHANGED = 0.3
FbiBusY_CHANGED = 0


# /////////// /////////// ///////// /// /////// //////////////////////// ////////////////////// //////////////////  / /
# Game Loop Easy
running = True
while running:
    # RGB
    screen.fill((71, 68, 68))

    # Background image
    screen.blit(background, (0, 0))

    for event in pygame.event.get():

        if event.type == pygame.QUIT:
            exit(0)

        # If keystroke is pressed check whether its R or L
        if event.type == pygame.KEYDOWN:
            # print("Pressed button")
            if event.key == pygame.K_LEFT:
                playerX_CHANGED = -5
            if event.key == pygame.K_RIGHT:
                playerX_CHANGED = 5
            if event.key == pygame.K_SPACE:
                # After fire bullet should follows the trajectory
                if bullet_state is "ready":
                    bullSound = mixer.Sound('soundOfShoot.wav')
                    bullSound.play()
                    # Get curr X cordinate
                    bulletX = playerX
                    fire_bullet(bulletX, bulletY)
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                playerX_CHANGED = 0

    # Check if Player hit the L or R boundary
    playerX += playerX_CHANGED
    if playerX <= 0:
        playerX = 0
    elif playerX >= 736:
        playerX = 736
    c = 1
    # Check if Policeman hit the L or R boundary
    for i in range(numOfChaser):

        # Dead player
        if (detachmentY[i] <= playerY and detachmentX[i] <= playerX + 70) or\
                (detachmentX[i] <= 600 and detachmentY[i] <= playerY + 20):
            for j in range(numOfChaser):
                detachmentY[j] = -200
                gameOver()
            break

        detachmentX[i] += detachmentX_CHANGED[i]
        if detachmentX[i] <= 0:
            detachmentX_CHANGED[i] = 3
            detachmentY[i] += detachmentY_CHANGED[i]
        elif detachmentX[i] >= 736:
            detachmentX_CHANGED[i] = -3
            detachmentY[i] += detachmentY_CHANGED[i]


        # Bullet reached the policeman
        reached = isItReached(detachmentX[i], detachmentY[i],
                              bulletX, bulletY)
        if reached:
            bullSound = mixer.Sound('deadPoliceman.wav')
            bullSound.play()
            bulletY = 50
            bullet_state = "ready"
            score += 1
            if score == 5:
                pygame.QUIT
                running = False
            # print(score)
            detachmentX[i] = random.randint(0, 700)
            detachmentY[i] = random.randint(520, 520)
        policeman(detachmentX[i], detachmentY[i], i)

    # Bullet movement
    if bulletY >= 550:
        bulletY = 50
        bullet_state = "ready"

    if bullet_state is "fire":
        fire_bullet(bulletX, bulletY)
        bulletY += bulletY_CHANGED

    player(playerX, playerY)
    showTheScore(textOfScoreX, textOfScoreY)
    pygame.display.update()

# Detachment: Hard
hardDetachmentImg = []
hardDetachmentX = []
hardDetachmentY = []
hardDetachmentX_CHANGED = []
hardDetachmentY_CHANGED = []
# Number of chaser
numOfChaser = 15

# Policeman
for i in range(numOfChaser):
    hardDetachmentImg.append(pygame.image.load('FBI_bus.png'))
    hardDetachmentX.append(random.randint(0, 700))
    hardDetachmentY.append(480)
    hardDetachmentX_CHANGED.append(3)
    hardDetachmentY_CHANGED.append(-60)

#Game loop hard
running = True
while running:
    # RGB
    screen.fill((71, 68, 68))

    # Background image
    screen.blit(background, (0, 0))

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit(0)

        # If keystroke is pressed check whether its R or L
        if event.type == pygame.KEYDOWN:
            #print("Pressed button")
            if event.key == pygame.K_LEFT:
                playerX_CHANGED = -5
            if event.key == pygame.K_RIGHT:
                playerX_CHANGED = 5
            if event.key == pygame.K_SPACE:
                # After fire bullet should follows the trajectory
                if bullet_state is "ready":
                    bullSound = mixer.Sound('soundOfShoot.wav')
                    bullSound.play()
                    # Get curr X cordinate of the spaceship
                    bulletX = playerX
                    fire_bullet(bulletX, bulletY)
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                playerX_CHANGED = 0

    # Check if Player hit the L or R boundary
    playerX += playerX_CHANGED
    if playerX <= 0:
        playerX = 0
    elif playerX >= 736:
        playerX = 736

    # Check if policeman or fbi bus hit the L or R boundary
    for i in range(numOfChaser):

        # Dead player
        if (hardDetachmentY[i] <= playerY and hardDetachmentX[i] <= playerX + 70) or\
                (hardDetachmentX[i] <= 600 and hardDetachmentY[i] <= playerY + 20):
            for j in range(numOfChaser):
                hardDetachmentY[j] = -200
                gameOver()
            if c == 1:
                f = open("ListOfScore.txt", "a")
                f.write("Player scored: " + str(score) + " (" + str(datetime.today())[0:19] + ")" + "\n")
                f.close()
                c += 1
            break

        hardDetachmentX[i] += hardDetachmentX_CHANGED[i]
        if hardDetachmentX[i] <= 0:
            hardDetachmentX_CHANGED[i] = 3
            hardDetachmentY[i] += hardDetachmentY_CHANGED[i]
        elif hardDetachmentX[i] >= 736:
            hardDetachmentX_CHANGED[i] = -3
            hardDetachmentY[i] += hardDetachmentY_CHANGED[i]


        # Bullet reached the policeman
        reached = isItReached(hardDetachmentX[i], hardDetachmentY[i],
                              bulletX, bulletY)
        if reached:
            bullSound = mixer.Sound('FBIbusDie.wav')
            bullSound.play()
            bulletY = 50
            bullet_state = "ready"
            score += 1
            # print(score)
            hardDetachmentX[i] = random.randint(0, 700)
            hardDetachmentY[i] = random.randint(550, 550)

        FbiBus(hardDetachmentX[i], hardDetachmentY[i], i)

    # Bullet movement
    if bulletY >= 550:
        bulletY = 50
        bullet_state = "ready"

    if bullet_state is "fire":
        fire_bullet(bulletX, bulletY)
        bulletY += bulletY_CHANGED

    player(playerX, playerY)
    showTheScore(textOfScoreX, textOfScoreY)
    pygame.display.update()
