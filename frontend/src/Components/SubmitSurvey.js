import React, {useState, useEffect} from 'react';
import useSharedFormStyle from './SharedFormStyle';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

const SubmitSurvey = () => {
    const classes = useSharedFormStyle();
    const [surveys, setSurveys] = useState([]);
    const [selectedSurvey, setSelectedSurvey] = useState({
        question:""
    });
    const [surveyAnswer, setSurveyAnswer] = useState({
        feedback: "",
        score: 0
    });

    useEffect(() => {
        fetch('/surveys')
            .then(response => response.json())
            .then(surveys => {
                console.log(surveys);
                setSurveys(surveys);
            });
     },[]);

    useEffect(() => {
        //show random survey
        if(surveys.length > 0) {
            setSelectedSurvey(surveys[Math.floor(Math.random() * surveys.length)]);
        }
    },[surveys]);

    const onSubmit = (e) => {
        e.preventDefault();

        if (surveyAnswer.feedback !== "" && surveyAnswer.score !== 0) {
            fetch('/submitAnswer?surveyId=' + selectedSurvey.surveyId, {
                method: 'POST',
                headers: {
                         'Content-Type': 'application/json',
            },
               body: JSON.stringify(surveyAnswer),
            }).then(response => response.text())
              .then(data => {
                   console.log('Success:', data);
              })
              .catch((error) => {
                   console.error('Error:', error);
            });
        }
    }

    const handleFeedbackChange = (e) => {
        setSurveyAnswer({
            ...surveyAnswer,
            feedback: e.target.value 
        });
    };

    const handleScoreChange = (e) => {
        setSurveyAnswer({
            ...surveyAnswer,
            score: e.target.value 
        });
    };


    return (
        <form className={classes.root} onSubmit={onSubmit}>
            <div>
                <TextField className={classes.textFieldClass} label={selectedSurvey.question} onChange = {handleFeedbackChange}/>
                <TextField type="number" className={classes.textFieldClass} 
                     label="Your Score" onChange = {handleScoreChange} 
                     InputProps={{ inputProps: { min: 0, max: 10 } }}/>
            </div>
            <Button type="submit" variant="contained" color="primary" className={classes.buttonClass}>
                Submit Your Answer
            </Button>
        </form>
    );
};

export default SubmitSurvey;


