<div align="center">
    <img src="https://avatars.githubusercontent.com/u/87736653" alt="Logo" width="150">
</div>

<div align="center">

<h1 align="center" style="margin: 0;">Datafitch</h1>
<p align="center" style="margin-top: 0; font-size: 1.2rem;">
    An fastest, qualified & easy to use multi database library 
    <br />
    <a href="https://github.com/Fitchle/datafitch/wiki"><strong>📖 Documents 📖</strong></a>
    <br />
    <br />
    <a href="https://github.com/Fitchle/datafitch/issues">Report Bug</a>
    ·
    <a href="https://github.com/Fitchle/datafitch/issues">Request Feature</a>
  </p>
</div>
<div align="center">
    <a href="https://github.com/orgs/Fitchle/people">
        <img src="https://img.shields.io/github/contributors/Fitchle/datafitch?style=for-the-badge"></img>
    </a>
    <a href="https://github.com/Fitchle/datafitch/network/members">
        <img src="https://img.shields.io/github/forks/Fitchle/datafitch?style=for-the-badge"></img>
    </a>
    <a href="https://github.com/Fitchle/datafitch/stargazers">
        <img src="https://img.shields.io/github/stars/Fitchle/datafitch?style=for-the-badge"></img>
    </a>
    <a href="https://github.com/Fitchle/datafitch/issues">
        <img src="https://img.shields.io/github/issues/Fitchle/datafitch?style=for-the-badge"></img>
    </a>
    <a href="https://github.com/Fitchle/datafitch/blob/main/LICENSE">
        <img src="https://img.shields.io/github/license/Fitchle/datafitch?style=for-the-badge"></img>
    </a>

</div>
<br/>
<br/>
<details>
  <summary style="font-size: 15px;">Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#contributors">Contributors</a></li>
  </ol>
</details>

<h2 style="margin: 0;">📗 About The Project</h2>
<p style="margin: 0; line-height: 0;">
BenDB is a useful & easy-to-use, multi-database library that supports multiple databases.
</p>

Here's why:

* It is an **easy to use** library, no need to waste time anymore!
* It is an **useful** library.
* It is an **multi-database** library, that supports multiple databases.

### Built With

<p style="margin: 0; line-height: 20px;">
In this project, HikariCP was used as connection pool and lombok was<br> used for code generation.
</p>

* [HikariCP](https://github.com/brettwooldridge/HikariCP)
* [Lombok](https://projectlombok.org)

## 🌙 Getting Started

### ✨ Installation

1. Firstly clone this project with Git

```xml

<dependency>
    <groupId>com.fitchle.datafitch</groupId>
    <artifactId>datafitch-%DB%</artifactId>
    <version>version</version>
</dependency>
```

2. Read the documents
3. Code, code, code!

## ⚡ Usage

<h4 style="margin-top: 30px;">Connecting to Database (MYSQL)</h4>

```java
    DatafitchMySQL mysql=new DatafitchMySQL("host",3306,"db","username","password");
        Connection conn=mysql.connect();
        // Do something
        conn.close();
```

For more examples, please refer to the [Documentation](https://gitbook.io)

## 🔐 License

Distributed under the MIT License. See `LICENSE` for more information.

## 📞 Contact

<a href="https://discordapp.com/users/309326498500968449"><img src="https://img.shields.io/badge/-Discord-black.svg?style=for-the-badge&logo=discord&logoColor=white&colorB=6366F1"></img></a>

## 🧑🏻‍💻 Contributors

<img src="https://i.ibb.co/cvBQ2Qj/Gimble-Logo-Design.png" width="100" style="border-radius: 15px"></img>
<img src="https://i.ibb.co/rHZn9SJ/pp-00000.png" width="100" style="border-radius: 15px"></img>
