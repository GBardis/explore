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
 
### Naming conventions:
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
