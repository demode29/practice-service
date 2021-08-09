import React from 'react';
import CommonTable from './CommonTable';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
    root: {
        display: 'flex',
        justifyContent: 'center'
    },
    child: {
        width: '50%'
    }
});

const Topics = (props) => {
    var headerContent = {
        id: "TopicId",
        secondColumn: "Topic",
        thirdColumn: "NPM Score"
    };
    const classes = useStyles();

    var tableContents = props.topics.map((fetchedValue) => {
        return {
            id: fetchedValue.topicId,
            secondColumn: fetchedValue.name,
            thirdColumn: fetchedValue.npmScore
        }
    });

    return (
        <div className={classes.root}>
            <div className={classes.child}>
                <CommonTable tableContents = {tableContents} 
                    headerContents = {headerContent}>
                </CommonTable>
            </div>
        </div>
    );
};

export default Topics;