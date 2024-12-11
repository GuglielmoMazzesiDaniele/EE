// Auxiliary variables
var kebab_current_index = 0;
var camel_current_index = 0;
var statistics = [];

// Function that clear the question container

// Given a target container and a kebab-case sample, creates buttons and load them into the container
function loadExperimentQuestion(type){
    // Verifying if the experiments is over
    if(kebab_current_index === kebab_samples.length && camel_current_index === kebab_samples.length){
        // Disabling the experiment
        document.getElementById("experiment-container").style.display = "none";
        // Enabling the download section
        document.getElementById("download-container").style.display = "block";
        // Creating the CSV
        generateCSV()
        // Leaving the current function
        return;
    }

    // Clearing the options container
    let optionsContainer = document.getElementById("experiment-options")
    optionsContainer.innerHTML = "";

    // Initializing the sample
    let sample;
    if(type === "kebab") {
        //Loading the sample
        sample = kebab_samples[kebab_current_index];
        // Increasing the samples index
        kebab_current_index++;
    }
    else {
        //Loading the sample
        sample = camel_samples[camel_current_index];
        // Increasing the samples index
        camel_current_index++;
    }

    // Loading the original question
    document.getElementById("question-original").textContent = sample.original;

    // Initializing the buttons
    let buttons = [];
    // Storing the starting time
    let startingTime = performance.now();
    // Initializing the current questions statistics
    let current_statistics = {
        errors: 0,
        elapsedTime: 0,
        type: type
    }

    // Initializing the distractors buttons
    sample.distractors.forEach(distractor => {
        // Creating the button
        const button = document.createElement("button");
        // Adding the text
        button.textContent = distractor;
        // Binding the click function
        button.addEventListener("click", () => {
            // Changing the background red
            button.style.background = "red";
            setTimeout(() => {
                button.style.background = "#007bff";
            }, 750, )
            // Increasing the amount of errors
            current_statistics.errors++;
        })
        // Pushing the button
        buttons.push(button);
    })

    // Initializing the correct button
    const correct_button = document.createElement("button");
    // Adding the text
    correct_button.textContent = sample.correct;
    // Binding the listener
    correct_button.addEventListener("click", () => {
        // Disabling all the buttons
        buttons.forEach(button => {
            button.disabled = true;
        })
        // Disabling the correct button
        correct_button.disabled = true;
        // Computing the elapsed time and storing it
        current_statistics.elapsedTime = performance.now() - startingTime;
        console.log(current_statistics);
        // Pushing the statistics
        statistics.push(current_statistics);
        // Loading the next question
        if(type === "kebab"){
            if(kebab_current_index === kebab_samples.length)
                setTimeout(loadExperimentQuestion("camel"), 1500)
            else
                setTimeout(loadExperimentQuestion("kebab"), 1500)
        }
        else {
            if (camel_current_index === camel_samples.length)
                setTimeout(loadExperimentQuestion("kebab"), 1500)
            else
                setTimeout(loadExperimentQuestion("camel"), 1500)
        }
    })
    // Pushing the button
    buttons.push(correct_button);

    // Shuffling the buttons
    shuffleArray(buttons);

    // Appending the buttons
    buttons.forEach(button => {
        optionsContainer.appendChild(button);
    })
}

// Function that shuffles a given array, used to randomize the buttons
function shuffleArray(array) {
    for (var i = array.length - 1; i >= 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// Function that generates a csv from the statistics of the experiment
function generateCSV(filename = 'data.csv') {
    // Check if the data array is not empty
    if (!statistics || !statistics.length) {
        console.error('No data to generate CSV.');
        return;
    }

    // Extract headers
    const headers = Object.keys(statistics[0]);

    // Append form data headers
    headers.push('Age', 'English Proficiency', 'Coding Proficiency', 'Dyslexia', 'Input Device');

    // Map the data array into CSV rows
    const rows = statistics.map(obj => {
        const row = headers.map(header => {
            if (header === 'Age') return JSON.stringify(age || '');
            if (header === 'English Proficiency') return JSON.stringify(english_proficiency || '');
            if (header === 'Coding Proficiency') return JSON.stringify(coding_proficiency || '');
            if (header === 'Dyslexia') return JSON.stringify(dyslexia || '');
            if (header === 'Input Device') return JSON.stringify(mouse || '');
            return JSON.stringify(obj[header] || '');
        });
        return row.join(',');
    });

    // Combine headers and rows into a CSV string
    const csvContent = [headers.join(','), ...rows].join('\n');

    // Create a Blob object
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });

    // Obtaining the link to download
    const link = document.getElementById('download-link');
    // Setting the text
    link.textContent = "here"
    // Creating the URL object
    const url = URL.createObjectURL(blob);
    // Setting the downloadable Blob
    link.setAttribute('href', url);
    link.setAttribute('download', filename);
}