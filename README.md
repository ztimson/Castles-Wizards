<!-- Header -->
<div id="top" align="center">
  <br />
  
  <!-- Logo -->
  <img src="https://git.zakscode.com/repo-avatars/3fd985444682d387cde8abb98775597a94a82f625e011736fc14e8858ca18ba4" alt="Logo" width="200" height="200">

  <!-- Title -->
  ### Castles & Wizards
  
  <!-- Description -->
  Grade 11 Final Project

  <!-- Repo badges -->
  [![Version](https://img.shields.io/badge/dynamic/json.svg?label=Version&style=for-the-badge&url=https://git.zakscode.com/api/v1/repos/ztimson/castles-and-wizards/tags&query=$[0].name)](https://git.zakscode.com/ztimson/castles-and-wizards/tags)
  [![Pull Requests](https://img.shields.io/badge/dynamic/json.svg?label=Pull%20Requests&style=for-the-badge&url=https://git.zakscode.com/api/v1/repos/ztimson/castles-and-wizards&query=open_pr_counter)](https://git.zakscode.com/ztimson/castles-and-wizards/pulls)
  [![Issues](https://img.shields.io/badge/dynamic/json.svg?label=Issues&style=for-the-badge&url=https://git.zakscode.com/api/v1/repos/ztimson/castles-and-wizards&query=open_issues_count)](https://git.zakscode.com/ztimson/castles-and-wizards/issues)

  <!-- Links -->

  ---
  <div>
    <a href="https://git.zakscode.com/ztimson/castles-and-wizards/releases" target="_blank">Release Notes</a>
    • <a href="https://git.zakscode.com/ztimson/castles-and-wizards/issues/new?template=.github%2fissue_template%2fbug.md" target="_blank">Report a Bug</a>
    • <a href="https://git.zakscode.com/ztimson/castles-and-wizards/issues/new?template=.github%2fissue_template%2fenhancement.md" target="_blank">Request a Feature</a>
  </div>

  ---
</div>

## Table of Contents
- [Castles & Wizards](#top)
  - [About](#about)
    - [Controls](#controls)
    - [Built With](#built-with)
  - [Setup](#setup)
    - [Production](#production)
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
  <h3 id="produciton" style="display: inline">
    Production
  </h3>
</summary>

#### Prerequisites
- [Java JDK](https://www.oracle.com/java/technologies/downloads/)

#### Instructions
1. Download the latest [release](https://git.zakscode.com/fhsons/castles-and-wizards/releases/)
2. Run `src/StartUp.java`

</details>

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
