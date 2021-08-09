import React, {useState, useEffect} from 'react';
import SurveyAnswers from './SurveyAnswers';
import Topics from './Topics';
import CreateSurvey from './CreateSurvey';
import SubmitSurvey from './SubmitSurvey';

import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';

function TabPanel(props) {
    const { children, value, index } = props;
  
    return (
      <div
        role="tabpanel"
        hidden={value !== index}
      >
        {children}
      </div>
    );
}

const MainContainer = () => {
    const [topics, setTopics] = useState([]);

    const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
      setValue(newValue);
    };

    useEffect(() => {
        fetch('/topics')
             .then(response => response.json())
             .then(topics => {
                setTopics(topics);
        }).catch((error) => {
            console.error('Error:', error)
          });
     },[]);

    return (
        <div>
            <Tabs value={value} onChange={handleChange} centered>
                <Tab value={0} label="Create Survey">
                </Tab>
                <Tab value={1} label="Submit Survey">
                </Tab>
                <Tab value={2} label="Topics">
                </Tab>
                <Tab value={3} label="Survey Answers">
                </Tab>
            </Tabs>

            <TabPanel value={value} index={0}>
                  <CreateSurvey></CreateSurvey>
            </TabPanel>

            <TabPanel value={value} index={1}>
                  <SubmitSurvey></SubmitSurvey>
            </TabPanel>

            <TabPanel value={value} index={2}>
                  <Topics topics = {topics}></Topics>
            </TabPanel>

            <TabPanel value={value} index={3}>
                <SurveyAnswers topics = {topics}></SurveyAnswers>
            </TabPanel>
        </div>
    );
};

export default MainContainer;