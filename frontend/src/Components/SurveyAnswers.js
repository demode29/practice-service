import React, {useState, useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';

import CommonTable from './CommonTable';


const useStyles = makeStyles({
    root: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexWrap: 'wrap'
    },
    topicClass: {
        width: '100%'
    },
    tableClass : {
        width: '50%'
    }
});

const SurveyAnswers = (props) => {
    const [currentTopic, setCurrentTopic] = useState(0);
    const [surveyAnswers, setSurveyAnswers] = useState([]);
    const handleChange = (e) => {
        setCurrentTopic(e.target.value);
    }
    const classes = useStyles();
   
    useEffect(() => {
        fetch('/getSurveyAnswersByTopic?topicId=' + currentTopic)
             .then(response => response.json())
             .then(surveyAnswers => {
                setSurveyAnswers(surveyAnswers);
             }).catch((error) => {
                 console.error(error);
             });
     },[currentTopic]);

     useEffect(() => {
         if(props.topics[0]) {
            fetch('/getSurveyAnswersByTopic?topicId=' + props.topics[0].topicId)
                .then(response => response.json())
                .then(surveyAnswers => {
                     setSurveyAnswers(surveyAnswers);
                }).catch((error) => {
                    console.error(error);
                });
         }
     },[props.topics]);

     var headerContent = {
        id: "SubmitId",
        secondColumn: "Score",
        thirdColumn: "Feedback"
     };

   return (
    <div className={classes.root}>
        <div className={classes.topicClass}>
            <p>Choose Topic:</p>
            <Select onChange={handleChange}>
                { props.topics.map((topic) => {
                    return (
                        <MenuItem value={topic.topicId}>{topic.name}</MenuItem>
                    );
                })}
            </Select>
        </div>
        <div className={classes.tableClass}>
            <CommonTable tableContents = {surveyAnswers.map((fetchedValue) => {
                return {
                    id: fetchedValue.submitId,
                    secondColumn: fetchedValue.score,
                    thirdColumn: fetchedValue.feedback
                }
                })} 
                headerContents = {headerContent}>
            </CommonTable>
        </div>
    </div>
   );
 };
 
 export default SurveyAnswers;