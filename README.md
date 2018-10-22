# Explore

## Additional Packages for Android Studio

### lombok (Required)

 1. Go to File > Settings > Plugins
 2. Click on Browse repositories...
 3. Search for Lombok Plugin
 4. Click on Install plugin
 5. Restart Android Studio 
 
### Save Actions (Optional)
 
 1. https://plugins.jetbrains.com/plugin/7642-save-actions
 
 
 
 
# Developer Guidelines & Conventions
 
### Naming conventions on xml files:
To add an  `@id` to an `.xml` element use `[control_type][fragment_name][action/data]`

Use this as reference for the elements you want to name
```
+---------------------+
| Element   | Prefix  |
|-----------+---------+
| TextView  | text_   |
| ImageView | image_  |
| Button    | button_ |
| Menu      | menu_   |
+-----------+---------+
```

For example, if you want to add an `@id` to a `TextView` control inside the `UserProfileFragment`  
which is used to show the User's `firstName` attribute, using the convention above you should have  
`@id/text_user_profile_first_name`  

### Naming reference variables in java files:
When creating a new variable that will be used to reference  
an xml element,the name should reflect the xml id  
e.g.: `TextView textViewFirstName;` or `Button buttonLogin;`  

This is stated here as a part of our team's convention  


__Before you start implementing, also have a quick read through__
a guideline file created by a company and we can actually use [__Android Guidlines__](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md)

***

### Commit message convention proposal

Commit messages are essentially a topic, so it's better if they are capitalized and do *not* contain  
a period (.)

e.g: `Fix button behaviour`

A commit message should answer the question  _If applied, this commit will_ `<your subject line here>`  
e.g.: _If applied, this commit will_ `Fix button behaviour`
    
 