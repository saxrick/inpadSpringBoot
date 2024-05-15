import useSWR from 'swr';
import Project from "../Project/Project.jsx";
import { request, setAuthHeader } from "../axios_helper.js";
import React from "react";
import data from "bootstrap/js/src/dom/data.js";

// const fetcher = (...args) => fetch(...args, {method: "GET", headers:{'Content-Type': 'application/json'}}).then((res) => res.json());
//
// export default function FetchProjects(){
//     const {
//         data: projects,
//         error,
//         isValidating,
//     } = useSWR('http://localhost:8080/projects/all', fetcher);
//
//     // Handles error and loading state
//     if (error) return <div className='failed'>failed to load</div>;
//     if (isValidating) return <div className="Loading">Loading...</div>;
//     console.log(projects)
//     // return projects
//     return (
//             <>
//                 {projects.length > 0 && projects.map((project) => <Project key={project.id} {...project}/>)}
//             </>
//         );
// }
export default class FetchProjects extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
        console.log(this.state.data)
    }

    componentDidMount() {
        request(
            "GET",
            "/projects/all",
            {}).then(
            (response) => {
                this.setState({data: response.data})
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                } else {
                    this.setState({data: error.response.code})
                }

            }
        );
    };
    render() {
        return(
            <>
                {this.state.data.length > 0 && this.state.data.map((project) => <Project key={project.id} {...project}/>)}
            </>
        )
    }
}


