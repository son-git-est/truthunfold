# Welcome to my project - **truth unfold**, an IT news website
---
Brief summary of what it has and does:

- 5 page website *(this can change)*
- home page: the welcome page with some information about the website. Here guests can find table listing all topics covered, number of articles in each topic, and visits to each topic *(total from all articles in the topic)*

    |topic|number of articles|visits|
    |---|---|---|
    |[business](#)|2|3445|
    |[education](#)|4|1256|
    |[technology](#)|7|8346|

- each topic name in the table will link to article page and will show articles in that topic only
- sidebar: side loaded, part of home page where guests can find the 3 latest pieces of news *(sorted by date added)*
- header & footer: jsp pages included in everyone page
- header contains tabs to navigate to pages of the site. Number and name of tabs *(as well as where they point to)* may change depending on user's type *(such as addmin, editor)*
- article: by default, all articles will show up. A filter is included so users can pick the fovourite topic
- account: log in/register function
- editor page: manage users(WIP) & manage articles(WIP)
- data is sourced from and written to MySQL

**Coming tasks**
- complete editor page
- forgot password function: email password reset link
- read more & read less
- record visit to article
- word editor
