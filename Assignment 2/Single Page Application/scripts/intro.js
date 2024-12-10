// Variables regarding the user
var age = -1;
var english_proficiency;
var coding_proficiency;

// Constant kebab-case sample used in the demonstration
const kebab_example_sample = {
    original: "paint wall",
    correct: "paint-wall",
    distractors: ["paint-well", "painter-wall", "paint-walls"],
    type: "kebab"
}

// Constant camelCase sample used in the demonstration
const camel_example_sample = {
    original: "paint wall",
    correct: "paintWall",
    distractors: ["paintWell", "painterWall", "paintWalls"],
    type: "camel"
}

// Function that loads the examples questions
function loadExampleQuestion(targetContainer, sample){
    // Initializing the buttons
    let buttons = [];

    // Initializing the correct button
    const correct_button = document.createElement("button");
    // Adding the text
    correct_button.textContent = sample.correct;
    // Binding a click listener
    correct_button.addEventListener("click", () => {
        // Changing background color
        correct_button.style.background = "green";

        // Flagging the correct response
        if(sample.type === "kebab"){
            // Hiding the kebab example
            document.getElementById("example-kebab-container").style.display = "none";
            // Showing the camel case example
            document.getElementById("example-camel-container").style.display = "block";
            // Showing filling the options container
            loadExampleQuestion(document.getElementById("camel-example"), camel_example_sample);
        }
        else{
            // Hiding the kebab example
            document.getElementById("example-camel-container").style.display = "none";
            // Showing the camel case example
            document.getElementById("form-container").style.display = "block";
        }
    })
    // Pushing the button
    buttons.push(correct_button);

    // Initializing the distractors buttons
    sample.distractors.forEach(distractor => {
        // Creating the button
        const button = document.createElement("button");
        // Adding the text
        button.textContent = distractor;
        // Binding a listener
        button.addEventListener("click", () => {
            button.style.background = "red";
            setTimeout(() => {
                button.style.background = "#007bff";
            }, 750, )
        })
        buttons.push(button);
    })

    // Shuffling the buttons
    shuffleArray(buttons);

    // Appending the buttons
    buttons.forEach(button => {
        targetContainer.appendChild(button);
    })
}

// Function that store the information input using the form
function storeUserInformation(){
    // Get form values
    age = document.getElementById("form-age").value;
    english_proficiency = document.getElementById("form-english-proficiency").value;
    coding_proficiency = document.getElementById("form-coding-proficiency").value;

    // Disabling the form button
    document.getElementById("form-button").disabled = true;

    // Displaying the start button
    document.getElementById("start-container").style.display = "block";
}

// Function that initialize the experiment
function startExperiment() {
    // Disabling the introduction
    document.getElementById("form-container").style.display = "none";
    // Enabling the experiment
    document.getElementById("experiment-container").style.display = "block";

    // Generating a random number to decide if to start with kebab or camel
    const randomNumber = Math.floor(Math.random() * 10) + 1;

    // Loading the first question (50% starting from kebab, 50% starting from camel)
    if(randomNumber <= 5)
        loadExperimentQuestion("kebab")
    else
        loadExperimentQuestion("camel")
}

// Execute function on load
document.addEventListener("DOMContentLoaded", () => {

    // Shuffling the samples
    shuffleArray(kebab_samples)
    shuffleArray(camel_samples)

    // Adding the listeners to the start demonstration button
    document.getElementById("start-demonstration").addEventListener("click", () => {
        // Hiding the explanation
        document.getElementById("rules-container").style.display = "none";
        // Showing the kebab case example
        document.getElementById("example-kebab-container").style.display = "block";
        // Showing filling the options container
        loadExampleQuestion(document.getElementById("kebab-example"), kebab_example_sample);
    })

    // Binding the start button with the corresponding function
    document.getElementById("start-button").addEventListener("click", startExperiment);
});