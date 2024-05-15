import useSWR from 'swr';
import Project from "../Project/Project.jsx";

const fetcher = (...args) => fetch(...args, {method: "GET", headers:{'Content-Type': 'application/json'}}).then((res) => res.json());

export default function FetchProjectByID({id}){
    const {
        data: project,
        error,
        isValidating,
    } = useSWR(`http://localhost:8080/projects/${id}`, fetcher);

    // Handles error and loading state
    if (error) return <div className='failed'>failed to load</div>;
    if (isValidating) return <div className="Loading">Loading...</div>;
    console.log(project)
    // return projects
    return (
        <>
            <Project {...project}/>
        </>
    );
}
