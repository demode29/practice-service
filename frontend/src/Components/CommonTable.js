import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
    table: {
      minWidth: 650,
    },
    headerClass : {
        fontWeight: 'bold'
    }
});

const CommonTable = (props) => {  
    var headerContents = props.headerContents;
    var tableContents = props.tableContents;
    const classes = useStyles();

    return (
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow >
                <TableCell className={classes.headerClass}>{headerContents.id}</TableCell>
                <TableCell className={classes.headerClass}>{headerContents.secondColumn}</TableCell>
                <TableCell className={classes.headerClass}>{headerContents.thirdColumn}</TableCell>
              </TableRow>
            </TableHead>
          <TableBody>
            {tableContents.map((content) => (
              <TableRow key={content.id}>
                <TableCell>{content.id}</TableCell>
                <TableCell>{content.secondColumn}</TableCell>
                <TableCell>{content.thirdColumn}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
  };
  
  export default CommonTable;