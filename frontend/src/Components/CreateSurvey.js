import React, {useState} from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import useSharedFormStyle from './SharedFormStyle';

const CreateSurvey = () => {
    const classes = useSharedFormStyle();
    const [survey, setSurvey] = useState({
        topic: { name: "" },
        question : ""
    });

    const onSubmit = (e) => {
        e.preventDefault();

        if(survey.topic.name !== "" && survey.question !== "") {
            fetch('/createSurveys', {
                method: 'POST',
                headers: {
                         'Content-Type': 'application/json',
            },
               body: JSON.stringify(survey),
            }).then(response => response.text())
              .then(data => {
                   console.log('Success:', data);
              })
              .catch((error) => {
                   console.error('Error:', error);
            });
        }
    }
    
    const handleTopicChange = (e) => {
        setSurvey({
            ...survey,
            topic: { name: e.target.value }
        });
    }

    const handleQuestionChange = (e) => {
        setSurvey({
            ...survey,
            question: e.target.value
        });
    }

    return (
        <form className={classes.root} onSubmit={onSubmit}>
            <div>
                <TextField className={classes.textFieldClass} label="Topic" onChange = {handleTopicChange}/>
                <TextField className={classes.textFieldClass} label="Question" onChange = {handleQuestionChange} />
            </div>
          <Button type="submit" variant="contained" color="primary" className={classes.buttonClass}>
                Create Survey
          </Button>
        </form>
    );
};

export default CreateSurvey;