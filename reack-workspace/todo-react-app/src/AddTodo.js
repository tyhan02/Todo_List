import React from 'react';
import { TextField, Paper, Grid, Button } from "@material-ui/core";

class AddTodo extends React.Component { // React.Component로 수정
    constructor(props) {
        super(props);
        this.state = { item: { title: "" } };
    }

    render() {
        return (
            <Paper style={{ margin: 16, padding: 16 }}>
                <Grid container>
                    <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
                        <TextField placeholder="Add Todo Here" fullWidth />
                    </Grid>
                    <Grid xs={1} md={1} item>
                        <Button fullWidth color="secondary" variant="outlined"> {/* fullWidth와 outlined로 수정 */}
                            +
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        );
    }
}

export default AddTodo;
