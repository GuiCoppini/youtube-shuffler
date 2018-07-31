# youtube-shuffler

## How to run:
- Go to the root folder of the project and run `./gradlew bootRun`
- Then, the Frontend of the project will be available at `http://localhost:8080/index.html`

---

## How it works:
- The frontend requests the .mp3 file inside the <audio> tag. As it is available, the frontend requests the backend the current time of the song and sets it in the <audio> element.
- The backend stores the start time of the song so it is able to tell the "requestees" how much time passed since the song started.
  
---

## Ideas:
- As it is unable to change songs and play next ones, the ideas are:
  - The backend returns the actual time of the song and the name of the song;
  - The frontend checks if this is the actual song; if not, it downloads the new one.
    - It might be possible to download next songs as the first one is playing.
