<!-- Header -->
<div id="top" align="center">
  <br />
  
  <!-- Logo -->
  <img src="https://git.zakscode.com/repo-avatars/3fd985444682d387cde8abb98775597a94a82f625e011736fc14e8858ca18ba4" alt="Logo" width="200" height="200">

  <!-- Title -->
  ### Castles & Wizards
  
  <!-- Description -->
  Grade 11 Final Project

</div>

## Table of Contents
- [Castles & Wizards](#top)
  - [About](#about)
    - [Controls](#controls)
    - [Built With](#built-with)
  - [Setup](#setup)
    - [Development](#development)
  - [License](#license)

## About

<img alt="Screenshot" src="./screenshot.gif" width="60%" height="auto">

This game was built as my grade 11 computer programing final project. The grading was based on demonstrating different programing structures such as ifs, loops, etc... I chose to build a simple RPG.

Castles and Wizards is a game engine that reads pre-made text files to create its enviroment including: chests, conversations & maps. Configuration files & maps can be found inside `/Objects/...`. 

The game comes with a basic map editor to aid in building maps quickly. Maps are named using coordinates to create a tiled world map (`00.txt` is spawn).

### Controls
- `WASD` to move
- `Space` to interact.

### Built With
[![Java](https://img.shields.io/badge/Java-5382A1?style=for-the-badge&logo=coffeescript&logoColor=F8981D)](https://java.com/)

## Setup

<details>
<summary>
  <h3 id="development" style="display: inline">
    Development
  </h3>
</summary>

#### Prerequisites
- [Java SDK](https://www.oracle.com/ca-en/java/technologies/downloads)

#### Instructions
1. Compile source code: `cd src && javac StartUp.java`
2. Start the game: `java StartUp`

</details>

## License
Copyright © 2023 Zakary Timson | All Rights Reserved

See the [license](./LICENSE) for more information.
