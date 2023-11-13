# Personal Corlaez Web

A Kotlin static web generator that includes:

* Markdown support and HTML DSL support
* Internationalization for both Markdown and HTML DSL
* Variables in CSS (such as assets paths)
* HTTP Server with dev mode that reloads pages upon regenerating the website

This project also uses experimental features in Kotlin such as the k2 compiler and context receivers

## Run Modes

| arg        | Outcome                               | Recommended Usage                       |
|------------|---------------------------------------|-----------------------------------------|
| dev        | generate and serve with hot reload    | Dev server                              |
| prd        | generate and serve without hot reload | Check website as it would look in prod  |
| regenerate | http request to regenerate website    | Run manually or use it in File Watchers |
| release    | generate as prd without serving       | Generate website for a deploy           |


## Writing a blog post

Markdown is the recommended way. Write the content in `src/main/resources/en/index.md` and `src/main/resources/es/index.md`


## HTML template

The template (header and footer) is defined with HTML DSL. A LanguageContext is available to insert translated text.

The text translations to be used on the template are defined in the Kotlin class LocalizedText. All languages for the same text are collocated, which helps keeping translations in sync.

## Optional Plugin: File Watcher

While pages served with the included server in dev mode will hot reload if the generation is executed we still have to trigger that manually. To avoid this, File Watcher plugin can be used to trigger generation when saving files.

This is the recommended configuration:

* File Type: Any (Alternatively one for each: kt, md, js)
* Scope: Open Files
* Program: Point for gradlew in the root of the project
* Arguments: run --args="regenerate"
* Auto-save edited files to trigger the watcher: Checked
* Trigger on external changes: Checked (IntelliJ CSS color picker triggers an external change)
* Trigger regardless of syntax errors: Unchecked
* Show console: Always

## Roadmap

* Support to write each blog post in a different page and generate index out of their titles
* Separate this project into a reusable library

## Notes for self

The banner was created using colorrandom with size 600x315 red as primary and some darker red as variation.
the old banner was 2048x1170, I can't remember if there was a reason for that.
The new banner upscales the 600x315 image to 2400x1260 and then adds the signature on top.
