import React from 'react';
import TableRow from './TableRow';

const Table = (props) => {  
  var headerContents = props.headerContents;
  var tableContents = props.tableContents;

  return (
    <table>
    <TableRow 
        isHeader={true}
        currentId={headerContents.id}
        secondColumn={headerContents.secondColumn}
        thirdColumn={headerContents.thirdColumn}
        key={0}>
    </TableRow>
    {
        tableContents.map(content => {
          return (
            <TableRow
              isHeader={false}
              currentId={content.id}
              secondColumn={content.secondColumn}
              thirdColumn={content.thirdColumn}
              key={content.id} />
          );
        })
    }
    </table>
  );
};

export default Table;